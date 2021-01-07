import random
import math


class Cromozom:
    def __init__(self, l):
        self.genotip = l
        self.fitness = 0

    def evaluare(self, weights, expected):
        suma = 0
        for i in range(len(weights)):
            suma += weights[i] * self.genotip[i]
        self.fitness = abs(expected - suma)


def initPopulatie(nr, nrgene):
    cromozomi = []
    for i in range(nr):
        l = [random.uniform(0.02, 0.06) for i in range(nrgene)]
        crom = Cromozom(l)
        cromozomi.append(crom)
    return cromozomi


def evalPopulatie(populatie, weights, expected):
    for individ in populatie:
        individ.evaluare(weights, expected)


def selectie(populatie):
    ind1 = random.randint(0, len(populatie) - 1)
    ind2 = random.randint(0, len(populatie) - 1)
    if populatie[ind1].fitness < populatie[ind2].fitness:
        return populatie[ind1]
    return populatie[ind2]


def XO(m, t):
    cromozom_l = []
    for i in range(len(m.genotip)):
        cromozom_l.append((m.genotip[i] + t.genotip[i] - 0.2) / (i + 1))
    return Cromozom(cromozom_l)


def mutatie(crom):
    poz = random.randint(0, len(crom.genotip) - 1)
    val = random.uniform(0.5, 0.7)
    op = random.randint(0, 1)
    if op == 0:
        crom.genotip[poz] += val
    else:
        crom.genotip[poz] -= val


def findBest(populatie):
    cromozom = populatie[0]
    for cr in populatie:
        if cr.fitness < cromozom.fitness:
            cromozom.genotip = cr.genotip
            cromozom.fitness = cr.fitness
    return cromozom


def findWorst(populatie):
    cromozom = populatie[0]
    for cr in populatie:
        if cr.fitness > cromozom.fitness:
            cromozom.genotip = cr.genotip
            cromozom.fitness = cr.fitness
    return cromozom


def alg_ev(nrEpoci, nrPop, inputs, outputs):
    populatie = initPopulatie(nrPop, len(inputs[0]))
    for epoca in range(nrEpoci):
        for i in range(len(inputs)):
            # print(epoca)
            evalPopulatie(populatie, inputs[i], outputs[i])
            m = selectie(populatie)
            t = selectie(populatie)
            off = XO(m, t)
            mutatie(off)
            off.evaluare(inputs[i], outputs[i])
            worst = findWorst(populatie)
            worst.evaluare(inputs[i], outputs[i])
            best = findBest(populatie)
        if off.fitness < worst.fitness:
            # print("ok")
            populatie.remove(worst)
            populatie.append(off)
        # print(str(epoca) + " " + str(findBest(populatie).fitness))
    # print()
    # print(findBest(populatie).genotip)
    # print(findBest(populatie).fitness)
    return findBest(populatie)


def predict(inputs, coeffs):
    result = 0
    for i in range(len(coeffs)):
        result += coeffs[i] * inputs[i]
    return result


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
    with open(file, "a") as f:
        f.write(str(accuracy) + '\n')


def test(input, coeffs):
    predictedLabels = []
    label = 0
    for i in range(len(input)):
        predicted = 0
        for j in range(len(coeffs)):
            predictedValue = predict(input[i], coeffs[j])
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


def runAlgEvolutiv(input_train, output_train, input_test, output_test, epochs, nopop):
    labels = []
    coeffs = []
    for label in output_train:
        if label not in labels:
            labels.append(label)
    labels.sort()
    for label in labels:
        new_output = output_train
        for e in new_output:
            if e != label:
                e = -1
        best = alg_ev(epochs, nopop, input_train, new_output)
        coeffs.append(best.genotip)

    computedLabels = test(input_test, coeffs)
    acc = accuracy(computedLabels, output_test)
    return acc


def run2():
    epochs = 200
    nrPop = 20
    cols_train, lines_train, input_train, output_train = readFromFile("hard_drive_01_dragos_train.txt")
    cols_test, lines_test, input_test, output_test = readFromFile("hard_drive_01_dragos_test.txt")
    print(runAlgEvolutiv(input_train, output_train, input_test, output_test, epochs, nrPop))


run2()
