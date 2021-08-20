import matplotlib.pyplot as plt
import numpy as np

pathToSerData = "testing/serial/"
pathToParData = "testing/parallel/"
pathToFigs = "testing/figs/"
fileName = ""
meansSerial = []
meansParallel = []

def process():
    # serial data
    S = []
    with open(pathToSerData+fileName) as f:
        lines = f.readlines()
    for line in lines:
        S.append(float(line))
    plt.plot(S,linewidth=2,label="Serial",c="#E26D5C")
    meansSerial.append(np.mean(S))

    # parallel data
    P = []
    with open(pathToParData+fileName) as f:
        lines = f.readlines()
    for line in lines:
        P.append(float(line))
    plt.plot(P,linewidth=2,label="Parallel",c="#4D7EA8")
    meansParallel.append(np.mean(P))

    # legend and save plot
    plt.legend(loc="upper right")
    plt.savefig(pathToFigs+fileName[:-4]+".jpg")
    plt.clf()
    


for i in [100,1000,10000]:
    for j in range(3,22,3):
        fileName = "{}-{}-out.txt".format(i,j)
        process()
    # calculate best filter width
    print("serial",meansSerial)
    print("parallel",meansParallel)
    