import random
import math
import numpy as np


def getMediiX(x):
    medii = []
    for i in range(len(x[0])):
        sum = 0
        for list in x:
            sum += list[i]
        medii.append(sum / len(x))
    return medii


def deviatiiX(x):
    deviatii = []
    medii = getMediiX(x)
    for i in range(len(x[0])):
        dev = 0
        for list in x:
            dev += (list[i] - medii[i]) ** 2
        deviatii.append(math.sqrt(dev / (len(x) - 1)))
    return deviatii


def deviatieY(y, N, medie):
    deviatie = 0
    for elem in y:
        deviatie += (elem - medie) ** 2
    deviatie = math.sqrt(deviatie / (N - 1))
    return deviatie


def normalizareX(x, medii, deviatii, N):
    for list in x:
        for i in range(N - 1):
            list[i] = abs((list[i] - medii[i]) / deviatii[i])
    return x


def normalizareY(y, deviatie, medie):
    yNormalizat = []
    for elem in y:
        yNormalizat.append(abs((elem - medie) / deviatie))
    return yNormalizat


def predict(coeffs, ex):
    y = 0
    for i in range(len(ex)):
        y += coeffs[i] * ex[i]
    return y


def sigmoid(x):
    return 1.0 / (1 + math.exp(-x))


def update(coeffs, x, y, LR):
    y_guess = []
    for item in x:
        y_guess.append(sigmoid(predict(coeffs, item)))
    for i in range(len(x)):
        err = y_guess[i] - y[i]
        for j in range(len(coeffs)):
            coeffs[j] -= LR * err*x[i][j]


def cost(coeffs, x, y):
    sum = 0
    for i in range(len(x)):
        y_guess = sigmoid(predict(coeffs, x[i]))
        t1 = y[i] * math.log(y_guess)
        t2 = (1 - y[i]) * math.log(1 - y_guess)
        sum += -(t1 + t2)
    return sum / len(x)


def train(x, y, LR, epochs, min_cost):
    coeffs = [random.uniform(0.01,0.02) for i in range(len(x[0]))]
    current_cost = abs(cost(coeffs, x, y))
    while current_cost > min_cost and epochs > 0:
        update(coeffs, x, y, LR)
        current_cost = cost(coeffs, x, y)
        epochs -= 1
    return coeffs


def readFromFile(file):
    y = []
    x = []
    with open(file, "r") as f:
        N = int(f.readline())
        lines = int(f.readline())
        for line in f.readlines():
            line = line.strip()
            line = line.split(",")
            x1 = [float(elem) for elem in line[:N - 1]]
            y1 = float(line[N - 1])
            x.append(x1)
            y.append(y1)
    return N, lines, x, y


def run():
    fileA = "example_parkinson_01_train.txt"
    N, lines, x, y = readFromFile(fileA)

    mediiX = getMediiX(x)
    deviatieX = deviatiiX(x)
    xNormalizat = normalizareX(x, mediiX, deviatieX, N)

    medieY = np.mean(y)
    devY = deviatieY(y, N, medieY)
    yNormalizat = normalizareY(y, devY, medieY)
    coeffs = train(xNormalizat, yNormalizat, 0.00001, 50000, 0.5)
    print(coeffs)
    print()

    fileT = "example_parkinson_01_test.txt"
    NTest, n, xTest, yTest = readFromFile(fileT)
    mediiTestX = getMediiX(xTest)
    deviatieTestX = deviatiiX(xTest)
    mediaTestY = np.mean(yTest)
    deviY = deviatieY(yTest, NTest, mediaTestY)
    xTestNormalizat = normalizareX(xTest,mediiTestX,deviatieTestX, NTest)

    sum = 0
    for i in range(NTest):
        yCalc = predict(coeffs, xTestNormalizat[i])
        yDenormalizat = yCalc * deviY + mediaTestY
        print(yDenormalizat)
        sum += (yDenormalizat - yTest[i]) ** 2

    sum /= NTest
    print(sum)


run()
