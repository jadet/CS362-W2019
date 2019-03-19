from __future__ import division
import commands
import os, re
from operator import itemgetter
def gcov():
    ret = runTarantula()
    os.system("gcov dominion.c")
    return ret

def runTarantula():
    (ret, output) = commands.getstatusoutput("cardTest1")
    if (ret == 0):
	ret = False
    else:
	ret = True
    return ret


def parseGcovFile(lines, passed, start):
    file = open("dominion.c.gcov")
    index = 0
    for line in file.readlines():

        lineStart = line[0:10]
        lineStart = re.sub("[-#: ]", "", lineStart)
	try:
	    numRuns = int(float(lineStart))
	except:
	    numRuns = 0
	
        if(testPassed):
	    if(start == True):
	        lines.append([numRuns, 0])
	    else:
	        lines[index][0] += numRuns
	else:
	    if(start == True):
		lines.append([0, numRuns])
	    else:
		lines[index][1] += numRuns
	index += 1
    file.close()
    return lines


def suspEval(lineRuns, passed, failed):
    susp = []
    lineNumber = -4
    for line in lineRuns:
	if(line[1] == 0):
            susp.append([0, lineNumber])
	else:
	    s = ((1.0)*(line[1]/failed))/((line[1]/failed) + (line[0])/passed) 
     	    susp.append([s, lineNumber])
	lineNumber += 1
    
    sorted(susp, key=itemgetter(0))
    
    print "\n\n"
    for l in susp:
	if(l[0] > 0):
	    print "Suspicious: %f\nLine: %i\n\n" %(l[0], l[1])
        
#main
totalFailed = 0
totalPassed = 0
start = True
lineRuns = []
for x in range(0, 100):
    testPassed = gcov()
    if(testPassed):
	totalPassed += 1
    else:
	totalFailed += 1
    lineRuns = parseGcovFile(lineRuns, testPassed, start)
    start = False
    
print "Total tests passed: %i\n Total tests failed: %i" % (totalPassed, totalFailed)
suspEval(lineRuns, totalPassed, totalFailed);


