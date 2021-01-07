import math
import random
import sklearn.neural_network
import numpy as np


class Perceptron:
    def __init__(self, weights):
        self.weights = weights
        self.output = 0
        self.delta = 0

    def process(self, inputs):
        sum = 0.0
        for i in range(len(inputs)):
            sum += self.weights[i] * inputs[i]
        sum += self.weights[len(inputs)]
        return sum

    def activate(self, inputs):
        sum = self.process(inputs)
        return 1.0 / (1.0 + math.exp(-sum))

    def inverse_activate(self):
        return self.output * (1.0 - self.output)

    def __str__(self):
        return str(self.weights) + " " + str(self.output) + " " + str(self.delta) + "\n"


def initializare(noIn, noOut, noOfHiddenNeurons):
    net = []
    # for j in range(nrOfHLayers):
    #     HL = []  # hidden layers
    #     for i in range(noIn):
    #         w = [random.random() for _ in range(noIn)]  # weights
    #         HL.append(Perceptron(w))
    HL = [Perceptron([random.random() for _ in range(noIn + 1)]) for _ in range(noOfHiddenNeurons)]
    net.append(HL)
    for i in range(noIn - 1):
        HL = [Perceptron([random.random() for _ in range(noOfHiddenNeurons + 1)]) for _ in range(noOfHiddenNeurons)]
        net.append(HL)

    OL = [Perceptron([random.random() for _ in range(noOfHiddenNeurons + 1)]) for _ in range(noOut)]
    #     = []  # output layer
    # for i in range(noOut):
    #     w = [random.random() for i in range(noIn)]  # output weights
    #     OL.append(Perceptron(w))
    net.append(OL)
    return net


def forwardPropagation(net, input):
    for layer in net:
        newInputs = []
        for neuron in layer:
            neuron.output = neuron.activate(input)
            newInputs.append(neuron.output)
        input = newInputs
    return input


def backwardPropagation(net, expected):
    for i in range(len(net) - 1, 0, -1):
        current_layer = net[i]
        errors = []

        if i == len(net) - 1:  # output layer
            for j in range(len(current_layer)):
                error = expected - current_layer[j].output
                errors.append(error)
        else:  # a hidden layer
            for j in range(len(current_layer)):
                current_error = 0.0
                next_layer = net[i + 1]
                for neuron in next_layer:
                    current_error += neuron.delta * neuron.weights[j]
                errors.append(current_error)
        for j in range(len(current_layer)):
            current_layer[j].delta = errors[j] * current_layer[j].inverse_activate()


def update(net, example, learningRate):
    for i in range(len(net)):
        inputs = example[:-1]
        if i > 0:
            inputs = [neuron.output for neuron in net[i - 1]]
        for neuron in net[i]:
            for j in range(len(inputs)):
                neuron.weights[j] += learningRate * neuron.delta * inputs[j]
            neuron.weights[-1] += learningRate * neuron.delta


def trainRegression(epochCount, lr, input, output):
    net = initializare(len(input[0]), 1, 5)
    for epoch in range(epochCount):
        for i in range(len(input)):
            forwardPropagation(net, input[i])
            backwardPropagation(net, output[i])
            update(net, input[i], lr)
    return net


def predictRegression(net, input):
    computedOutputs = []
    for i in range(len(input)):
        out = forwardPropagation(net, input[i])
        computedOutputs.append(out[0])
    return computedOutputs


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


def writeToFile(file, accuracy):
    with open(file, "w") as f:
        f.write(str(accuracy) + '\n')


def calculateError(predicted_output, real_output):
    sum = 0
    for i in range(len(real_output)):
        sum += (real_output[i] - predicted_output[i]) ** 2
    return sum / len(real_output)


def runLReg(inputTrain, outputTrain, inputTest, outputTest, learningRate, noEpochs):
    ann = sklearn.neural_network.MLPRegressor((48,), "logistic", "sgd")
    ann.fit(inputTrain, outputTrain)
    ann.predict(inputTrain)

    computedOutputs = ann.predict(inputTest)
    print("Eroare: ", calculateError(computedOutputs, outputTest))


def run():
    epochs = 8000
    learningRate = 0.001
    cols, lines, input, output = readFromFile("hard_parkinson_dragos_01_train")
    cols_test, lines_test, input_test, output_test = readFromFile("hard_parkinson_dragos_01_test")
    # coeffs = trainRegression(epochs, learningRate, input, output)
    # predicted_output = predictRegression(coeffs, input_test)
    # accuracy = calculateError(predicted_output, output_test)
    # print(accuracy)
    runLReg(input, output, input_test, output_test, learningRate, epochs)


run()
