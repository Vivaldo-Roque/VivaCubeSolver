#from kociemba import solve
from color import color
from tracker import tracker

def solver(path):

    #with open('scramble.txt', 'r') as myfile:
    #   data=myfile.read().replace('\n', '')
        
    #data=solve(data)
    t = tracker(path)
    c = color(t)
    return c
    
#print(solver("fotos"))
#input()