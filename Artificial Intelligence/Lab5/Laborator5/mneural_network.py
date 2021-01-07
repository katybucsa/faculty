import random
import math


class Neuron:
    def __init__(self, w=[], out=None, delta=0.0):
        self.weights = w
        self.output = out
        self.delta = delta

    def __str__(self):
        return "weights: " + str(self.weights) + ", output: " + str(self.output) + ", delta: " + str(self.delta)

    def __repr__(self):
        return "weights: " + str(self.weights) + ", output: " + str(self.output) + ", delta: " + str(self.delta)


# initialisation of the weights for each neuron of all the layers (input layer & hidden layers)
def netInitialisation(noInputs, noOutputs, noHiddenNeurons):
    net = []
    '''hiddenLayer = []
    for h in range(noHiddenNeurons): #create hidden layers
    weights = [ random() for i in range(noInputs + 1)] #noInputs and the bias
    neuron = Neuron(weights)
    hiddenLayer.append(neuron)'''
    hiddenLayer = [Neuron([random.random() for i in range(noInputs + 1)]) for h in range(noHiddenNeurons)]
    net.append(hiddenLayer)
    '''outputLayer = []
    for o in range(noOutputs):
    weights = [ random() for i in range(noHiddenNeurons + 1)]
    neuron = Neuron(weights)
    outputLayer.append(neuron) '''
    outputLayer = [Neuron([random.random() for i in range(noHiddenNeurons + 1)]) for o in range(noOutputs)]
    net.append(outputLayer)
    return net


def activate(input, weights):
    result = 0.0
    for i in range(0, len(input)):
        result += input[i] * weights[i]
    result += weights[len(input)]
    return result


# neuron transfer
def transfer(value):
    return 1.0 / (1.0 + math.exp(-value))


# neuron computation/activation
def forwardPropagation(net, inputs):
    for layer in net:
        newInputs = []
        for neuron in layer:
            activation = activate(inputs, neuron.weights)
            neuron.output = transfer(activation)
            newInputs.append(neuron.output)
        inputs = newInputs
    return inputs


# inverse transfer of a neuron
def transferInverse(val):
    return val * (1 - val)


# error propagation
def backwardPropagation(net, expected):
    for i in range(len(net) - 1, 0, -1):
        crtLayer = net[i]
        errors = []
    if i == len(net) - 1:  # last layer
        for j in range(0, len(crtLayer)):
            crtNeuron = crtLayer[j]
            errors.append(expected[j] - crtNeuron.output)
    else:  # hidden layers
        for j in range(0, len(crtLayer)):
            crtError = 0.0
            nextLayer = net[i + 1]
            for neuron in nextLayer:
                crtError += neuron.weights[j] * neuron.delta
            errors.append(crtError)
    for j in range(0, len(crtLayer)):
        crtLayer[j].delta = errors[j] * transferInverse(crtLayer[j].output)


# change the weights
def updateWeights(net, example, learningRate):
    for i in range(0, len(net)):  # for each layer
        inputs = example[:-1]
    if i > 0:  # hidden layers or output layer
        inputs = [neuron.output for neuron in net[i - 1]]  # computed values of precedent layer
    for neuron in net[i]:  # update weight of all neurons of the current layer
        for j in range(len(inputs)):
            neuron.weights[j] += learningRate * neuron.delta * inputs[j]
        neuron.weights[-1] += learningRate * neuron.delta


def trainingMLP(net, data, noOutputTypes, learningRate, noEpochs):
    for epoch in range(0, noEpochs):
        sumError = 0.0
        for example in data:
            inputs = example[:- 1]
            computedOutputs = forwardPropagation(net, inputs)
            expected = [example[-1]]
            crtErr = sum([(expected[i] - computedOutputs[i]) ** 2 for i in range(0, len(expected))])
            sumError += crtErr
            backwardPropagation(net, expected)
            updateWeights(net, example, learningRate)


def evaluatingMLP(net, data):
    computedOutputs = []
    for inputs in data:
        computedOutput = forwardPropagation(net, inputs[:-1])
        computedOutputs.append(computedOutput[0])
    return computedOutputs


def computePerformanceRegression(computedOutputs, realOutputs):
    error = sum([(computedOutputs[i] - realOutputs[i]) ** 2 for i in range(len(computedOutputs))])
    return error / len(realOutputs)


def runMLP(trainData, testData, learningRate, noEpochs):
    noInputs = len(trainData[0]) - 1
    noOutputs = 1
    net = netInitialisation(noInputs, noOutputs, 2)

    trainingMLP(net, trainData, noOutputs, learningRate, noEpochs)
    realOutputs = [trainData[i][j] for j in range(len(trainData[0]) - 1, len(trainData[0])) for i in
                   range(0, len(trainData))]
    computedOutputs = evaluatingMLP(net, trainData)
    print("train SRE: ", computePerformanceRegression(computedOutputs, realOutputs))

    realOutputs = [testData[i][j] for j in range(len(testData[0]) - 1, len(testData[0])) for i in
                   range(0, len(testData))]
    computedOutputs = evaluatingMLP(net, testData)
    print("test SRE: ", computePerformanceRegression(computedOutputs, realOutputs))


def regression():
    # y = 2*x1 + x2 - x3
    regressionDataTrain = [[0.00662, 3.38e-05, 28.199],
                           [0.0081, 6.375e-05, 11.218],
                           [0.00273, 1.57e-05, 23.856],
                           [0.00382, 3.005e-05, 11.27],
                           [0.00501, 3.648e-05, 31.0],
                           [0.00453, 3.481e-05, 27.729],
                           [0.00526, 6.016e-05, 16.816],
                           [0.0087, 4.743e-05, 18.169],
                           [0.0124, 0.00011434, 17.0],
                           [0.00476, 4.216e-05, 12.0]]
    regressionDataTest = [[0.00355, 2.694e-05, 17.889],
                          [0.00644, 4.285e-05, 20.354],
                          [0.00495, 2.524e-05, 16.148]]

    # regressionDataTrain2 = [[0.74, 0.42, 0.97, -0.33911],
    #                         [0.04, 0.76, 0.79, -0.73327],
    #                         [0.72, 0.89, 0.13, 1.1539],
    #                         [0.13, 0.26, 0.14, -0.07017],
    #                         [0.65, 0.49, 0.79, -0.14347],
    #                         [0.43, 0.44, 0.70, -0.31482],
    #                         [0.86, 0.68, 0.99, 0.17052],
    #                         [0.73, 0.39, 0.29, 0.27971],
    #                         [0.08, 0.96, 0.56, -0.41447],
    #                         [0.47, 0.12, 0.72, -0.60652]]
    # regressionDataTest2 = [[0.31, 0.55, 0.82, 0.80]]
    learningRate = 0.001
    noEpochs = 200

    # trainingMLP()
    #
    # evaluatingMLP(net, data)

    runMLP(regressionDataTrain, regressionDataTest, learningRate, noEpochs)
    # runMLP_tool(regressionDataTrain, regressionDataTest, learningRate, noEpochs)


regression()
