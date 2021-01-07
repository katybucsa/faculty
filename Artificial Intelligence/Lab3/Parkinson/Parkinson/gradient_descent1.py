import math
import numpy as np
from random import random
from sklearn import linear_model
from math import isclose
import random
from numpy import linalg

from sklearn.preprocessing import StandardScaler


def ReadFromFile(filename):
    coeffs = []
    y = []
    suma = 0
    filepath = filename
    with open(filepath) as fp:
        n = int(fp.readline())
        N = int(fp.readline())
        copyN = N
        i = 0
        while copyN:
            line = fp.readline().rstrip("\n")
            elem = line.split(",")

            list = [float(el) for el in elem[:n - 1]]
            coeffs.append(list)
            y.append(float(elem[-1]))
            suma += float(elem[-1])
            copyN -= 1
        return n, N, coeffs, y, suma


def Medii(listOfList, n, N):
    medii = [0] * (n - 1)
    for list in listOfList:
        for i in range(n - 1):
            el = list[i]
            medii[i] = medii[i] + el
    for i in range(n - 1):
        medii[i] = medii[i] / N
    return medii


def DeviatiiStandard(listOfLists, n, N, medii):
    deviatii = [0] * (n - 1)
    for list in listOfLists:
        for i in range(n - 1):
            number = list[i]
            deviatii[i] = deviatii[i] + (number - medii[i]) ** 2
    for i in range(n - 1):
        deviatii[i] = math.sqrt(deviatii[i] / (N - 1))
    return deviatii


def DeviatieY(y, n, N, medie):
    deviatie = 0
    for elem in y:
        deviatie += (elem - medie) ** 2
    deviatie = math.sqrt(deviatie / (N - 1))
    return deviatie


"""def Tool(x, y, ep):
    clasificator = linear_model.LogisticRegression()
    clasificator.max_iter = ep
    clasificator.fit(x, y)
    return x,y"""


def NormalizareDate(listOfList, n, N, deviatiiStandard, medii):
    matrix = np.zeros((N, n - 1))
    line = 0
    for list in listOfList:
        for i in range(n - 1):
            el = list[i]
            matrix[line, i] = (abs((el - medii[i]) / deviatiiStandard[i]))
        line += 1
    return matrix


def normalizare(y, deviatie, medie, N):
    yNormalizat = []
    for elem in y:
        yNormalizat.append(abs((elem - medie) / deviatie))
    return yNormalizat


def Predict(coeffs, ex):
    y = 0
    for i in range(len(ex)):
        y = y + coeffs[i] * ex[i]
    return y


def Sigmoid(x):
    return 1 / (1 + math.exp(-x))


def UpdateCoeffs(coeffs, LR, x, y):
    yGuess = []
    for item in x:
        yGuess.append(Sigmoid(Predict(coeffs, item)))
    for j in range(len(x)):
        gradient = 0
        eroare = yGuess[j] - y[j]
        for i in range(len(coeffs)):
            coeffs[i] -= LR * eroare * x[j][i]


def Cost(x, y, coeffs):
    sum = 0
    for i in range(len(x)):
        yGuess = Sigmoid(Predict(coeffs, x[i]))
        t1 = y[i] * math.log(yGuess)
        t2 = (1 - y[i]) * math.log(1 - yGuess)
        sum -= (t1 + t2)
    return sum / len(x)


def Train(x, y, LR, epoci, min_cost):
    coeffs = []
    for i in range(len(x[0])):
        coeffs.append(random.uniform(2.47, 2.5))
    current_cost = abs(Cost(x, y, coeffs))
    while current_cost > min_cost and epoci > 0:
        UpdateCoeffs(coeffs, LR, x, y)
        current_cost = Cost(x, y, coeffs)
        epoci -= 1
    return coeffs


def Test(input, coeffs):
    y = Sigmoid(Predict(coeffs, input))
    if y > 0.5:
        return 1
    else:
        return 0


def Acuratete(x, y, coeffs):
    corect = 0
    for i in range(len(x)):
        yGuess = Test(x[i], coeffs)
        if isclose(yGuess, y[i], abs_tol=0.5):
            corect += 1


def getBetaLeastSquares(xTrain, yTrain):
    t = xTrain.T
    inv = linalg.inv(np.dot(t, xTrain))
    a = np.dot(inv, t)
    return np.dot(a, yTrain)


def getYcalc(x, beta):
    return np.dot(x, beta)


def getError(yTest, yCalc):
    err = []
    for i in range(len(yTest)):
        err.append((yTest[i] - yCalc[i]) ** 2)
    sum = 0
    for i in range(len(err)):
        sum = sum + err[i]
    return sum / len(err)


def Main():

    #TRAIN
    n, N, xTrain, yTrain, sumaY = ReadFromFile(
        "C:\\Users\\Damaris\\PycharmProjects\\Tema3\\Parkinson\\example_parkinson_01_train.txt")
    medieY = sumaY / N
    medii = Medii(xTrain, n, N)
    deviatii = DeviatiiStandard(xTrain, n, N, medii)
    xNormalizat = NormalizareDate(xTrain, n, N, deviatii, medii)
    deviatieY = DeviatieY(yTrain, n, N, medieY)
    yNormalizat = normalizare(yTrain, deviatieY, medieY, N)
    coeffs = Train(xNormalizat, yNormalizat, 0.00001, 80000, 0.5)

    #TEST
    nTest, NTest, xTest, yTest, sumaYTest = ReadFromFile(
        "C:\\Users\\Damaris\\PycharmProjects\\Tema3\\Parkinson\\example_parkinson_01_test")
    mediiTest = Medii(xTest, nTest, NTest)
    mediaYTest = sumaYTest / NTest
    deviatieYTest = DeviatieY(yTest, nTest, NTest, mediaYTest)
    yNormalizatTest = normalizare(yTest, deviatieYTest, mediaYTest, NTest)
    deviatiiTest = DeviatiiStandard(xTest, nTest, NTest, mediiTest)
    xNormalizatTest = NormalizareDate(xTest, nTest, NTest, deviatiiTest, mediiTest)

    #GD
    sum = 0
    for i in range(NTest):
        yCalc = Predict(coeffs, xNormalizatTest[i])
        yDenormalizat=yCalc*deviatieYTest+mediaYTest
        sum += (yDenormalizat - yTest[i]) ** 2
    sum /= NTest
    print(sum)

    #LSQ
    beta = getBetaLeastSquares(xNormalizat, yNormalizat)
    yCalc_LSQ = getYcalc(xNormalizatTest, beta)
    resLSQ = getError(yNormalizatTest, yCalc_LSQ)
    resDenorm = resLSQ * deviatieYTest + mediaYTest
    print(resDenorm)

Main()
