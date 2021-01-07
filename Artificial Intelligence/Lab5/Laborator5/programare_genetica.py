# import random
#
# MAX_DEPTH = 2
# FUNCTION_SET = ["+", "-", "*"]  # it can be extended
# TERMINAL_SET = [0, 1]  # no of features = 2; it can be extended
#
#
# class Chromosome:
#     def __init__(self):
#         self.representation = []
#         self.fitness = 0.0
#
#     def grow(self, crtDepth):
#         if crtDepth == MAX_DEPTH:  # select a terminal
#             terminal = random.choice(TERMINAL_SET)
#             self.representation.append(terminal)
#         else:  # select a function or a terminal
#             if random.random() < 0.5:
#                 terminal = random.choice(TERMINAL_SET)
#                 self.representation.append(terminal)
#             else:
#                 function = random.choice(FUNCTION_SET)
#                 self.representation.append(function)
#                 self.grow(crtDepth + 1)
#                 self.grow(crtDepth + 1)
#
#     def eval(self, inExample, pos):
#         if self.representation[pos] in TERMINAL_SET:
#             print(self.representation)
#             print(pos)
#             return inExample[self.representation[pos]]
#         else:
#             if self.representation[pos] == "+":
#                 pos += 1
#                 left = self.eval(inExample, pos)
#                 pos += 1
#                 right = self.eval(inExample, pos)
#                 return left + right
#             elif self.representation[pos] == "-":
#                 pos += 1
#                 left = self.eval(inExample, pos)
#                 pos += 1
#                 right = self.eval(inExample, pos)
#                 return left - right
#             elif self.representation[pos] == "*":
#                 pos += 1
#                 left = self.eval(inExample, pos)
#                 pos += 1
#                 right = self.eval(inExample, pos)
#                 return left * right
#
#     def computeFitness(self, inData, outData):
#         err = 0.0
#         for i in range(len(inData)):
#             crtEval = self.eval(inData[i], 0)
#             crtErr = abs(crtEval - outData[i]) ** 2
#             err += crtErr
#         self.fitness = err
#
#     def __str__(self):
#         return str(self.representation)  # + " fit = " + str(self.fitness)
#
#     def __repr__(self):
#         return str(self.representation)  # + " fit = " + str(self.fitness)
#
#
# def init(pop, noGenes, popSize):
#     for i in range(0, popSize):
#         indiv = Chromosome()
#         indiv.grow(0)
#         pop.append(indiv)
#
#
# def evalPop(pop, trainInput, trainOutput):
#     for indiv in pop:
#         indiv.computeFitness(trainInput, trainOutput)
#
#
# # binary tournament selection
# # def selection(pop):
# #     pos1 = random.randrange(len(pop))
# #     pos2 = random.randrange(len(pop))
# #     if (pop[pos1].fitness < pop[pos2].fitness):
# #         return pop[pos1]
# #     else:
# #         return pop[pos2]
#
#
# # roulette selection
# def selectionRoulette(pop):
#     sectors = [0]
#     sum = 0.0
#     for chromo in pop:
#         sum += chromo.fitness
#     for chromo in pop:
#         sectors.append(1 - (chromo.fitness / sum + sectors[len(sectors) - 1]))
#     r = random.random()
#     i = 1
#     while i < len(sectors) and sectors[i] <= r:
#         i += 1
#     return pop[i - 1]
#
#
# def traverse(repres, pos):
#     if repres[pos] in TERMINAL_SET:
#         return pos + 1
#     else:
#         pos = traverse(repres, pos + 1)
#         pos = traverse(repres, pos)
#         return pos
#
#
# # cutting-point XO
# # replace a sub-tree from M with a sub-tree from F
# def crossover(M, F):
#     off = Chromosome()
#     # a sub-tree of M (starting and ending points)
#     startM = random.randrange(len(M.representation))
#     endM = traverse(M.representation, startM)
#     # a sub-tree of F (starting and ending points)
#     startF = random.randrange(len(F.representation))
#     endF = traverse(F.representation, startF)
#     for i in range(0, startM):
#         off.representation.append(M.representation[i])
#     for i in range(startF, endF):
#         off.representation.append(F.representation[i])
#     for i in range(endM, len(M.representation)):
#         off.representation.append(M.representation[i])
#     return off
#
#
# # change the content of a note (function -> function, terminal -> terminal
# def mutation(off):
#     pos = random.randrange(len(off.representation))
#     if off.representation[pos] in TERMINAL_SET:
#         terminal = random.choice(TERMINAL_SET)
#         off.representation[pos] = terminal
#     else:
#         function = random.choice(FUNCTION_SET)
#         off.representation[pos] = function
#     return off
#
#
# def bestSolution(pop):
#     best = pop[0]
#     for indiv in pop:
#         if indiv.fitness < best.fitness:
#             best = indiv
#     return best
#
#
# def EA_steadyState(noGenes, popSize, noGenerations, trainIn, trainOut):
#     pop = []
#
#     init(pop, noGenes, popSize)
#     evalPop(pop, trainIn, trainOut)
#     for g in range(0, noGenerations):
#         for k in range(0, popSize):
#             # M = selection(pop)
#             # F = selection(pop)
#             M = selectionRoulette(pop)
#             F = selectionRoulette(pop)
#             off = crossover(M, F)
#             off = mutation(off)
#             off.computeFitness(trainIn, trainOut)
#             crtBest = bestSolution(pop)
#             if off.fitness < crtBest.fitness:
#                 crtBest = off
#     sol = bestSolution(pop)
#     return sol
#
#
# def readFromFile(file):
#     with open(file, 'r') as f:
#         cols = int(f.readline())
#         lines = int(f.readline())
#         x = []
#         y = []
#         for line in f.readlines():
#             line = line.strip()
#             if len(line) > 0:
#                 elems = line.split(",")
#                 list = [float(elem) for elem in elems]
#                 x.append(list[:cols - 1])
#                 y.append(list[-1])
#     return cols, lines, x, y
#
#
# def runEA():
#     colsTrain, linesTrain, inputTrain, outputTrain = readFromFile("example_parkinson_01_train.txt")
#     colsTest, linesTest, inputTest, outputTest = readFromFile("example_parkinson_01_test.txt")
#
#     learntModel = EA_steadyState(2, 10, 10, inputTrain, outputTrain)
#     print("learnt model: " + str(learntModel))
#     print("training quality: ", learntModel.fitness)
#     # learntModel.computeFitness(inputTest, outputTest)
#     # print("testing quality: ", learntModel.fitness)
#
#
# runEA()

import numpy as np
import sklearn.feature_selection
from sklearn import datasets, linear_model
# from genetic_selection import GeneticSelectionCV

import random

FUNCTIONS_SET = ["+", "-", "*"]
TERMINALS_SET = [0, 1]
D_MAX = 5


class Cromozom:
    def __init__(self):
        self.repr = []
        self.fitness = 0.0

    def grow(self, current_level=0):
        if current_level == D_MAX:
            val = random.choice(TERMINALS_SET)
            self.repr.append(val)
        else:
            if random.random() > 0.5:
                val = random.choice(FUNCTIONS_SET)
                self.repr.append(val)
                self.grow(current_level + 1)
                self.grow(current_level + 1)
            else:
                val = random.choice(TERMINALS_SET)
                self.repr.append(val)

    def eval(self, input, poz=0):
        if self.repr[poz] in TERMINALS_SET:
            return input[self.repr[poz]], poz
        else:
            pozOp = poz
            left, poz = self.eval(input, poz + 1)
            right, poz = self.eval(input, poz + 1)
            if self.repr[pozOp] == "+":
                return left + right, poz
            elif self.repr[pozOp] == "-":
                return left - right, poz
            else:
                return left * right, poz

    def computeFitness(self, inputs, outputs):
        _sum = 0
        for i in range(len(inputs)):
            val, p = self.eval(inputs[i])
            _sum += (val - outputs[i]) ** 2
        self.fitness = _sum / len(inputs)
        return _sum / len(inputs)


def generatePop(popSize):
    C = []
    for i in range(popSize):
        crom = Cromozom()
        crom.grow()
        C.append(crom)

    return C


def eval(pop, input, output):
    for e in pop:
        e.computeFitness(input, output)


def selectie(pop):
    fit_sum = 0
    for c in pop:
        fit_sum += c.fitness
    sectors = [0]
    for c in pop:
        sectors.append(1 - (c.fitness / fit_sum + sectors[-1]))
    rand = random.random()
    for i in range(len(sectors) - 1, -1, -1):
        if sectors[i] > rand:
            return pop[i]
    return pop[-1]


def traverse(poz, list):
    if list[poz] in TERMINALS_SET:
        return poz + 1
    new_poz = traverse(poz + 1, list)
    return traverse(new_poz, list)


def XO(m, f):
    child = Cromozom()
    start_m = random.randrange(len(m.repr))
    start_f = random.randrange(len(f.repr))
    end_m = traverse(start_m, m.repr)
    end_f = traverse(start_f, f.repr)
    for i in range(start_m):
        child.repr.append(m.repr[i])
    for i in range(start_f, end_f):
        child.repr.append(f.repr[i])
    for i in range(end_m, len(m.repr)):
        child.repr.append(m.repr[i])
    return child


def mutation(chrom):
    poz = random.randint(0, len(chrom.repr) - 1)
    if chrom.repr[poz] in FUNCTIONS_SET:
        chPoz = random.choice(FUNCTIONS_SET)
        chrom.repr[poz] = chPoz
    else:
        ch = random.choice(TERMINALS_SET)
        chrom.repr[poz] = ch


def findWorst(pop):
    worst = pop[0]
    for c in pop:
        if c.fitness > worst.fitness:
            worst = c
    return worst


def findBest(pop):
    best = pop[0]
    for c in pop:
        if c.fitness < best.fitness:
            best = c
    return best


def ga(nopop, noEpochs, inputs, outputs):
    pop = generatePop(nopop)
    for _ in range(noEpochs):
        eval(pop, inputs, outputs)
        m = selectie(pop)
        t = selectie(pop)
        c = XO(m, t)
        c.computeFitness(inputs, outputs)
        mutation(c)
        c1 = findWorst(pop)
        if c.fitness < c1.fitness:
            print(str(c.fitness) + "\n")
            pop.remove(c1)
            pop.append(c)
    return findBest(pop)


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


def GA(inputTrain, outputTrain, inputTest, outputTest):
    estimator = linear_model.LogisticRegression(solver="liblinear", multi_class="ovr")

    # selector = sklearn.feature_selection.GenericUnivariateSelect(estimator,
    #                                                              cv=5,
    #                                                              verbose=1,
    #                                                              scoring="accuracy",
    #                                                              max_features=5,
    #                                                              n_population=50,
    #                                                              crossover_proba=0.5,
    #                                                              mutation_proba=0.2,
    #                                                              n_generations=40,
    #                                                              crossover_independent_proba=0.5,
    #                                                              mutation_independent_proba=0.05,
    #                                                              tournament_size=3,
    #                                                              n_gen_no_change=10,
    #                                                              caching=True,
    #                                                              n_jobs=-1)
    # selector = selector.fit(inputTrain, outputTrain)
    #
    # print(selector.support_)


def run():
    nopop = 100
    epochs = 100
    colsTrain, linesTrain, inputTrain, outputTrain = readFromFile("example_parkinson_01_train.txt")
    print(ga(nopop, epochs, inputTrain, outputTrain).fitness)
    colsTest, linesTest, inputTest, outputTest = readFromFile("example_parkinson_01_test.txt")
    # GA(inputTrain, outputTrain, inputTest, outputTest)


run()
