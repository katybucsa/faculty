import math
import numpy as np
import random
from math import isclose
from sklearn import linear_model
from numpy import linalg


def ReadFromFile(filename):
    coeffs = []
    y = []
    suma = 0
    with open(filename, "r") as fp:
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
    coeffs = [random.uniform(0.45, 0.75) for i in range(len(x[0]))]
    current_cost = abs(Cost(x, y, coeffs))
    while current_cost > min_cost and epoci > 0:
        UpdateCoeffs(coeffs, LR, x, y)
        current_cost = Cost(x, y, coeffs)
        epoci -= 1
    return coeffs


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


def writeToFile(error1, error2, file):
    with open(file, "w") as f:
        f.write(str(error1) + "\n")
        f.write(str(error2))


def readFromFile(file):
    y = []
    x = []
    N = 0
    lines = 0
    sumY = 0
    with open(file, "r") as f:
        for line in f.readlines():
            lines += 1
            line = line.strip()
            line = line.split(",")
            x1 = [float(elem) for elem in line[:len(line) - 1]]
            y1 = float(line[len(line) - 1])
            sumY += y1
            N = len(line)
            x.append(x1)
            y.append(y1)

    return N, lines, x, y, sumY


def getMatrice(x, n, N):
    matrice = np.zeros((N, n - 1))
    for i in range(N):
        list = x[i]
        for j in range(n - 1):
            matrice[i, j] = list[j]
    return matrice


def Main():
    n, N, xTrain, yTrain, sumaY = ReadFromFile(
        "D:\A2S2\IA\Lab\Lab3\Parkinson\Parkinson\medium_parkinson_dragos_01_train")
    medieY = sumaY / N

    medii = Medii(xTrain, n, N)
    deviatii = DeviatiiStandard(xTrain, n, N, medii)

    xNormalizat = NormalizareDate(xTrain, n, N, deviatii, medii)
    deviatieY = DeviatieY(yTrain, n, N, medieY)
    yNormalizat = normalizare(yTrain, deviatieY, medieY, N)
    # print()
    coeffs = Train(xNormalizat, yNormalizat, 0.00001, 50000, 0.5)
    print(coeffs)

    n2, N2, xTest, yTest, sumaYTest = ReadFromFile(
        "D:\A2S2\IA\Lab\Lab3\Parkinson\Parkinson\medium_parkinson_dragos_01_test")
    medii2 = Medii(xTest, n2, N2)
    mediaYTest = sumaYTest / N2
    deviatieYTest = DeviatieY(yTest, n2, N2, mediaYTest)
    yNormalizatTest = normalizare(yTest, deviatieY, mediaYTest, N2)
    deviatii2 = DeviatiiStandard(xTest, n2, N2, medii2)
    xNormalizatTest = NormalizareDate(xTest, n2, N2, deviatii2, medii2)
    # print(xNormalizatTest)

    # print(yNormalizatTest)
    print()

    sum = 0
    for i in range(N2):
        yCalc = Predict(coeffs, xNormalizatTest[i])
        yDenormalizat = yCalc * deviatieYTest + mediaYTest
        # print(yDenormalizat)
        sum += (yDenormalizat - yTest[i]) ** 2
    sum /= N2
    print(sum)

    # LSQ

    xTrainMatrice = getMatrice(xTrain, n, N)
    xTestMatrice = getMatrice(xTest, n2, N2)
    beta = getBetaLeastSquares(xTrainMatrice, yTrain)
    yCalc_LSQ = getYcalc(xTestMatrice, beta)
    resLSQ = getError(yTest, yCalc_LSQ)
    # resDenorm = resLSQ * deviatieYTest + mediaYTest
    print(resLSQ)
    writeToFile(resLSQ, sum, "result.txt")


Main()

'''
n, N, xTrain, yTrain, sumaY = readFromFile("parkinsons_updrs.data")
medieY = sumaY / N

medii = Medii(xTrain, n, N)
deviatii = DeviatiiStandard(xTrain, n, N, medii)

xNormalizat = NormalizareDate(xTrain, n, N, deviatii, medii)
deviatieY = DeviatieY(yTrain, n, N, medieY)
yNormalizat = normalizare(yTrain, deviatieY, medieY, N)
# print()
coeffs = Train(xNormalizat, yNormalizat, 0.00001, 50000, 0.5)
print(coeffs)
'''
