from sklearn import linear_model
from random import random
import numpy as np

import math


def prediction(example, coef):
    s = 0.0
    for i in range(0, len(example)):
        s += coef[i] * example[i]
    return s


def sigmoidFunction(z):
    np.abs(-2)
    return 1.0 / (1.0 + math.exp(0.0 - z))


def cost_function(input, output, coef):
    noData = len(input)
    totalCost = 0.0
    for i in range(len(input)):
        example = input[i]
        predictedValue = sigmoidFunction(prediction(example, coef))
        # print(predictedValue)
        realLabel = output[i]
        class1_cost = realLabel * math.log(predictedValue)
        if predictedValue == 1:
            class2_cost = 1 - realLabel
        else:
            class2_cost = (1 - realLabel) * math.log(1 - predictedValue)
        crtCost = - class1_cost - class2_cost
        totalCost += crtCost
    return totalCost / noData


def updateCoefs(input, output, coef, learningRate):
    noData = len(input)
    predictedValues = []
    realLabels = []
    for j in range(noData):
        crtExample = input[j]
        predictedValues.append(sigmoidFunction(prediction(crtExample, coef)))
        realLabels.append(output[j])
    for i in range(len(coef)):
        gradient = 0.0
        for j in range(noData):
            crtExample = input[j]
            gradient = gradient + crtExample[i] * (predictedValues[j] - realLabels[j])
        coef[i] = coef[i] - gradient * learningRate
    return coef


def train(input, output, learningRate, noIter):
    coef = [random() for i in range(len(input[0]))]
    costs = []
    for it in range(noIter):
        coef = updateCoefs(input, output, coef, learningRate)
        crtCost = cost_function(input, output, coef)
        costs.append(crtCost)
    return costs, coef


def test(input, coeffs):
    predictedLabels = []
    label = 0
    for i in range(len(input)):
        predicted = -9999
        for j in range(len(coeffs)):
            predictedValue = sigmoidFunction(prediction(input[i], coeffs[j]))
            if predictedValue > predicted:
                predicted = predictedValue
                label = j + 1
        predictedLabels.append(label)
    return predictedLabels


def accuracy(computedLabels, realLabels):
    noMatches = 0
    for i in range(len(computedLabels)):
        if computedLabels[i] == realLabels[i]:
            noMatches += 1
    return noMatches / len(computedLabels)


def myLogisticRegression(input, input_test, output, output_test, learningRate, noIter):
    labels = []
    coeffs = []
    for label in output:
        if label not in labels:
            labels.append(label)
    labels.sort()
    for label in labels:
        new_output = output
        for e in new_output:
            if e != label:
                e = 1.0
            else:
                e = 0.0
        costs, coeficients = train(input, new_output, learningRate, noIter)
        coeffs.append(coeficients)

    computedLabels = test(input_test, coeffs)
    acc = accuracy(computedLabels, output_test)
    return acc


# def myLogisticRegression(x, y, learningRate, noEpoch):
#     logreg = linear_model.LogisticRegression(solver='liblinear', multi_class='auto')
#     logreg.max_iter = noEpoch
#     logreg.fit(x, y)
#     correct = sum(y == logreg.predict(x))
#     return correct / len(x)


def readFromFile(file):
    with open(file, 'r') as f:
        cols = int(f.readline())
        lines = int(f.readline())
        x = []
        y = []
        for line in f.readlines():
            line = line.strip()
            if len(line) > 0:
                elems = line.split(",")
                list = [float(elem) for elem in elems]
                x.append(list[:cols - 1])
                y.append(list[-1])
    return cols, lines, x, y


def testLogisticSGD():
    cols, lines, input, output = readFromFile("hard_drive_01_dragos_train.txt")
    cols_test, lines_test, input_test, output_test = readFromFile("hard_drive_01_dragos_test.txt")
    print("accuracy = ", myLogisticRegression(input, input_test, output, output_test, 0.001, 15000))
    # print("accuracy = ", myLogisticRegression(input_test, output_test, 0.001, 1000))


testLogisticSGD()
