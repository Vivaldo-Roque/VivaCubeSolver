from PyQt5 import QtWidgets, uic
from PyQt5.QtGui import QColor
import kociemba

color=QColor(255, 255, 255)
scolor=[None,'U','U','U','U','U','U','U','U','U','R','R','R','R','R','R','R','R','R','F','F','F','F','F','F','F','F','F','D','D','D','D','D','D','D','D','D','L','L','L','L','L','L','L','L','L','B','B','B','B','B','B','B','B','B',None]
scramble=""
texto=""

def getcolor(x):
    global color
    if x =="red":
        color=QColor(255, 0, 0)
    elif x =="orange":
        color=QColor(255, 165, 0)
    elif x =="blue":
        color=QColor(0, 0, 255)
    elif x =="green":
        color=QColor(0, 128, 0)
    elif x =="yellow":
        color=QColor(255, 255, 0)
    elif x =="white":
        color=QColor(255, 255, 255)
def solve():
    global scramble
    scramble="".join(scolor[1:55])
    condition=""
    try:
        condition = kociemba.solve(scramble)
    except:
        pass

    if condition.strip():
        solver.Scramble.setText(condition)
    elif condition is '':
        solver.Scramble.setText("Invalid pattern try again click RESET")


def reset():
    solver.wb1.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb2.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb3.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb4.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb6.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb7.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb8.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")
    solver.wb9.setStyleSheet("QPushButton {background-color: rgb(255, 255, 255);}")

    solver.yb1.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb2.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb3.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb4.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb6.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb7.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb8.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")
    solver.yb9.setStyleSheet("QPushButton {background-color: rgb(255, 255, 0);}")

    solver.rb1.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb2.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb3.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb4.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb6.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb7.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb8.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")
    solver.rb9.setStyleSheet("QPushButton {background-color: rgb(255, 0, 0);}")

    solver.ob1.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob2.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob3.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob4.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob6.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob7.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob8.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")
    solver.ob9.setStyleSheet("QPushButton {background-color: rgb(255, 165, 0);}")

    solver.bb1.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb2.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb3.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb4.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb6.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb7.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb8.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")
    solver.bb9.setStyleSheet("QPushButton {background-color: rgb(0, 0, 255);}")

    solver.gb1.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb2.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb3.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb4.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb6.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb7.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb8.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.gb9.setStyleSheet("QPushButton {background-color: rgb(0, 128, 0);}")
    solver.Scramble.setText("")
    global scolor
    scolor = [None, 'U', 'U', 'U', 'U', 'U', 'U', 'U', 'U', 'U', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'F', 'F',
              'F', 'F', 'F', 'F', 'F', 'F', 'F', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'L', 'L', 'L', 'L', 'L',
              'L', 'L', 'L', 'L', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', None]


#WB fuction
def wb1():
    solver.wb1.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[1] = "R"
    elif color==QColor(255, 165, 0):
        scolor[1] = "L"
    elif color==QColor(0, 0, 255):
        scolor[1] = "B"
    elif color==QColor(0, 128, 0):
        scolor[1] = "F"
    elif color==QColor(255, 255, 0):
        scolor[1] = "D"
    elif color==QColor(255, 255, 255):
        scolor[1] = "U"
def wb2():
    solver.wb2.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[2] = "R"
    elif color==QColor(255, 165, 0):
        scolor[2] = "L"
    elif color==QColor(0, 0, 255):
        scolor[2] = "B"
    elif color==QColor(0, 128, 0):
        scolor[2] = "F"
    elif color==QColor(255, 255, 0):
        scolor[2] = "D"
    elif color==QColor(255, 255, 255):
        scolor[2] = "U"
def wb3():
    solver.wb3.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[3] = "R"
    elif color==QColor(255, 165, 0):
        scolor[3] = "L"
    elif color==QColor(0, 0, 255):
        scolor[3] = "B"
    elif color==QColor(0, 128, 0):
        scolor[3] = "F"
    elif color==QColor(255, 255, 0):
        scolor[3] = "D"
    elif color==QColor(255, 255, 255):
        scolor[3] = "U"
def wb4():
    solver.wb4.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[4] = "R"
    elif color==QColor(255, 165, 0):
        scolor[4] = "L"
    elif color==QColor(0, 0, 255):
        scolor[4] = "B"
    elif color==QColor(0, 128, 0):
        scolor[4] = "F"
    elif color==QColor(255, 255, 0):
        scolor[4] = "D"
    elif color==QColor(255, 255, 255):
        scolor[4] = "U"
def wb6():
    solver.wb6.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[6] = "R"
    elif color==QColor(255, 165, 0):
        scolor[6] = "L"
    elif color==QColor(0, 0, 255):
        scolor[6] = "B"
    elif color==QColor(0, 128, 0):
        scolor[6] = "F"
    elif color==QColor(255, 255, 0):
        scolor[6] = "D"
    elif color==QColor(255, 255, 255):
        scolor[6] = "U"
def wb7():
    solver.wb7.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[7] = "R"
    elif color==QColor(255, 165, 0):
        scolor[7] = "L"
    elif color==QColor(0, 0, 255):
        scolor[7] = "B"
    elif color==QColor(0, 128, 0):
        scolor[7] = "F"
    elif color==QColor(255, 255, 0):
        scolor[7] = "D"
    elif color==QColor(255, 255, 255):
        scolor[7] = "U"
def wb8():
    solver.wb8.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[8] = "R"
    elif color==QColor(255, 165, 0):
        scolor[8] = "L"
    elif color==QColor(0, 0, 255):
        scolor[8] = "B"
    elif color==QColor(0, 128, 0):
        scolor[8] = "F"
    elif color==QColor(255, 255, 0):
        scolor[8] = "D"
    elif color==QColor(255, 255, 255):
        scolor[8] = "U"
def wb9():
    solver.wb9.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[9] = "R"
    elif color==QColor(255, 165, 0):
        scolor[9] = "L"
    elif color==QColor(0, 0, 255):
        scolor[9] = "B"
    elif color==QColor(0, 128, 0):
        scolor[9] = "F"
    elif color==QColor(255, 255, 0):
        scolor[9] = "D"
    elif color==QColor(255, 255, 255):
        scolor[9] = "U"
#WB fuction
#RB fuction
def rb1():
    solver.rb1.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[10] = "R"
    elif color==QColor(255, 165, 0):
        scolor[10] = "L"
    elif color==QColor(0, 0, 255):
        scolor[10] = "B"
    elif color==QColor(0, 128, 0):
        scolor[10] = "F"
    elif color==QColor(255, 255, 0):
        scolor[10] = "D"
    elif color==QColor(255, 255, 255):
        scolor[10] = "U"
def rb2():
    solver.rb2.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[11] = "R"
    elif color==QColor(255, 165, 0):
        scolor[11] = "L"
    elif color==QColor(0, 0, 255):
        scolor[11] = "B"
    elif color==QColor(0, 128, 0):
        scolor[11] = "F"
    elif color==QColor(255, 255, 0):
        scolor[11] = "D"
    elif color==QColor(255, 255, 255):
        scolor[11] = "U"
def rb3():
    solver.rb3.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[12] = "R"
    elif color==QColor(255, 165, 0):
        scolor[12] = "L"
    elif color==QColor(0, 0, 255):
        scolor[12] = "B"
    elif color==QColor(0, 128, 0):
        scolor[12] = "F"
    elif color==QColor(255, 255, 0):
        scolor[12] = "D"
    elif color==QColor(255, 255, 255):
        scolor[12] = "U"
def rb4():
    solver.rb4.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[13] = "R"
    elif color==QColor(255, 165, 0):
        scolor[13] = "L"
    elif color==QColor(0, 0, 255):
        scolor[13] = "B"
    elif color==QColor(0, 128, 0):
        scolor[13] = "F"
    elif color==QColor(255, 255, 0):
        scolor[13] = "D"
    elif color==QColor(255, 255, 255):
        scolor[13] = "U"
def rb6():
    solver.rb6.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[15] = "R"
    elif color==QColor(255, 165, 0):
        scolor[15] = "L"
    elif color==QColor(0, 0, 255):
        scolor[15] = "B"
    elif color==QColor(0, 128, 0):
        scolor[15] = "F"
    elif color==QColor(255, 255, 0):
        scolor[15] = "D"
    elif color==QColor(255, 255, 255):
        scolor[15] = "U"
def rb7():
    solver.rb7.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[16] = "R"
    elif color==QColor(255, 165, 0):
        scolor[16] = "L"
    elif color==QColor(0, 0, 255):
        scolor[16] = "B"
    elif color==QColor(0, 128, 0):
        scolor[16] = "F"
    elif color==QColor(255, 255, 0):
        scolor[16] = "D"
    elif color==QColor(255, 255, 255):
        scolor[16] = "U"
def rb8():
    solver.rb8.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[17] = "R"
    elif color==QColor(255, 165, 0):
        scolor[17] = "L"
    elif color==QColor(0, 0, 255):
        scolor[17] = "B"
    elif color==QColor(0, 128, 0):
        scolor[17] = "F"
    elif color==QColor(255, 255, 0):
        scolor[17] = "D"
    elif color==QColor(255, 255, 255):
        scolor[17] = "U"
def rb9():
    solver.rb9.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[18] = "R"
    elif color==QColor(255, 165, 0):
        scolor[18] = "L"
    elif color==QColor(0, 0, 255):
        scolor[18] = "B"
    elif color==QColor(0, 128, 0):
        scolor[18] = "F"
    elif color==QColor(255, 255, 0):
        scolor[18] = "D"
    elif color==QColor(255, 255, 255):
        scolor[18] = "U"
#RB fuction
#GB fuction
def gb1():
    solver.gb1.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[19] = "R"
    elif color==QColor(255, 165, 0):
        scolor[19] = "L"
    elif color==QColor(0, 0, 255):
        scolor[19] = "B"
    elif color==QColor(0, 128, 0):
        scolor[19] = "F"
    elif color==QColor(255, 255, 0):
        scolor[19] = "D"
    elif color==QColor(255, 255, 255):
        scolor[19] = "U"
def gb2():
    solver.gb2.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[20] = "R"
    elif color==QColor(255, 165, 0):
        scolor[20] = "L"
    elif color==QColor(0, 0, 255):
        scolor[20] = "B"
    elif color==QColor(0, 128, 0):
        scolor[20] = "F"
    elif color==QColor(255, 255, 0):
        scolor[20] = "D"
    elif color==QColor(255, 255, 255):
        scolor[20] = "U"
def gb3():
    solver.gb3.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[21] = "R"
    elif color==QColor(255, 165, 0):
        scolor[21] = "L"
    elif color==QColor(0, 0, 255):
        scolor[21] = "B"
    elif color==QColor(0, 128, 0):
        scolor[21] = "F"
    elif color==QColor(255, 255, 0):
        scolor[21] = "D"
    elif color==QColor(255, 255, 255):
        scolor[21] = "U"
def gb4():
    solver.gb4.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[22] = "R"
    elif color==QColor(255, 165, 0):
        scolor[22] = "L"
    elif color==QColor(0, 0, 255):
        scolor[22] = "B"
    elif color==QColor(0, 128, 0):
        scolor[22] = "F"
    elif color==QColor(255, 255, 0):
        scolor[22] = "D"
    elif color==QColor(255, 255, 255):
        scolor[22] = "U"
def gb6():
    solver.gb6.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[24] = "R"
    elif color==QColor(255, 165, 0):
        scolor[24] = "L"
    elif color==QColor(0, 0, 255):
        scolor[24] = "B"
    elif color==QColor(0, 128, 0):
        scolor[24] = "F"
    elif color==QColor(255, 255, 0):
        scolor[24] = "D"
    elif color==QColor(255, 255, 255):
        scolor[24] = "U"
def gb7():
    solver.gb7.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[25] = "R"
    elif color==QColor(255, 165, 0):
        scolor[25] = "L"
    elif color==QColor(0, 0, 255):
        scolor[25] = "B"
    elif color==QColor(0, 128, 0):
        scolor[25] = "F"
    elif color==QColor(255, 255, 0):
        scolor[25] = "D"
    elif color==QColor(255, 255, 255):
        scolor[25] = "U"
def gb8():
    solver.gb8.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[26] = "R"
    elif color==QColor(255, 165, 0):
        scolor[26] = "L"
    elif color==QColor(0, 0, 255):
        scolor[26] = "B"
    elif color==QColor(0, 128, 0):
        scolor[26] = "F"
    elif color==QColor(255, 255, 0):
        scolor[26] = "D"
    elif color==QColor(255, 255, 255):
        scolor[26] = "U"
def gb9():
    solver.gb9.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[27] = "R"
    elif color==QColor(255, 165, 0):
        scolor[27] = "L"
    elif color==QColor(0, 0, 255):
        scolor[27] = "B"
    elif color==QColor(0, 128, 0):
        scolor[27] = "F"
    elif color==QColor(255, 255, 0):
        scolor[27] = "D"
    elif color==QColor(255, 255, 255):
        scolor[27] = "U"
#GB fuction
#YB fuction
def yb1():
    solver.yb1.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[28] = "R"
    elif color==QColor(255, 165, 0):
        scolor[28] = "L"
    elif color==QColor(0, 0, 255):
        scolor[28] = "B"
    elif color==QColor(0, 128, 0):
        scolor[28] = "F"
    elif color==QColor(255, 255, 0):
        scolor[28] = "D"
    elif color==QColor(255, 255, 255):
        scolor[28] = "U"
def yb2():
    solver.yb2.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[29] = "R"
    elif color==QColor(255, 165, 0):
        scolor[29] = "L"
    elif color==QColor(0, 0, 255):
        scolor[29] = "B"
    elif color==QColor(0, 128, 0):
        scolor[29] = "F"
    elif color==QColor(255, 255, 0):
        scolor[29] = "D"
    elif color==QColor(255, 255, 255):
        scolor[29] = "U"
def yb3():
    solver.yb3.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[30] = "R"
    elif color==QColor(255, 165, 0):
        scolor[30] = "L"
    elif color==QColor(0, 0, 255):
        scolor[30] = "B"
    elif color==QColor(0, 128, 0):
        scolor[30] = "F"
    elif color==QColor(255, 255, 0):
        scolor[30] = "D"
    elif color==QColor(255, 255, 255):
        scolor[30] = "U"
def yb4():
    solver.yb4.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[31] = "R"
    elif color==QColor(255, 165, 0):
        scolor[31] = "L"
    elif color==QColor(0, 0, 255):
        scolor[31] = "B"
    elif color==QColor(0, 128, 0):
        scolor[31] = "F"
    elif color==QColor(255, 255, 0):
        scolor[31] = "D"
    elif color==QColor(255, 255, 255):
        scolor[31] = "U"
def yb6():
    solver.yb6.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[33] = "R"
    elif color==QColor(255, 165, 0):
        scolor[33] = "L"
    elif color==QColor(0, 0, 255):
        scolor[33] = "B"
    elif color==QColor(0, 128, 0):
        scolor[33] = "F"
    elif color==QColor(255, 255, 0):
        scolor[33] = "D"
    elif color==QColor(255, 255, 255):
        scolor[33] = "U"
def yb7():
    solver.yb7.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[34] = "R"
    elif color==QColor(255, 165, 0):
        scolor[34] = "L"
    elif color==QColor(0, 0, 255):
        scolor[34] = "B"
    elif color==QColor(0, 128, 0):
        scolor[34] = "F"
    elif color==QColor(255, 255, 0):
        scolor[34] = "D"
    elif color==QColor(255, 255, 255):
        scolor[34] = "U"
def yb8():
    solver.yb8.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[35] = "R"
    elif color==QColor(255, 165, 0):
        scolor[35] = "L"
    elif color==QColor(0, 0, 255):
        scolor[35] = "B"
    elif color==QColor(0, 128, 0):
        scolor[35] = "F"
    elif color==QColor(255, 255, 0):
        scolor[35] = "D"
    elif color==QColor(255, 255, 255):
        scolor[35] = "U"
def yb9():
    solver.yb9.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[36] = "R"
    elif color==QColor(255, 165, 0):
        scolor[36] = "L"
    elif color==QColor(0, 0, 255):
        scolor[36] = "B"
    elif color==QColor(0, 128, 0):
        scolor[36] = "F"
    elif color==QColor(255, 255, 0):
        scolor[36] = "D"
    elif color==QColor(255, 255, 255):
        scolor[36] = "U"
#YB fuction
#OB fuction
def ob1():
    solver.ob1.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[37] = "R"
    elif color==QColor(255, 165, 0):
        scolor[37] = "L"
    elif color==QColor(0, 0, 255):
        scolor[37] = "B"
    elif color==QColor(0, 128, 0):
        scolor[37] = "F"
    elif color==QColor(255, 255, 0):
        scolor[37] = "D"
    elif color==QColor(255, 255, 255):
        scolor[37] = "U"
def ob2():
    solver.ob2.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[38] = "R"
    elif color==QColor(255, 165, 0):
        scolor[38] = "L"
    elif color==QColor(0, 0, 255):
        scolor[38] = "B"
    elif color==QColor(0, 128, 0):
        scolor[38] = "F"
    elif color==QColor(255, 255, 0):
        scolor[38] = "D"
    elif color==QColor(255, 255, 255):
        scolor[38] = "U"
def ob3():
    solver.ob3.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[39] = "R"
    elif color==QColor(255, 165, 0):
        scolor[39] = "L"
    elif color==QColor(0, 0, 255):
        scolor[39] = "B"
    elif color==QColor(0, 128, 0):
        scolor[39] = "F"
    elif color==QColor(255, 255, 0):
        scolor[39] = "D"
    elif color==QColor(255, 255, 255):
        scolor[39] = "U"
def ob4():
    solver.ob4.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[40] = "R"
    elif color==QColor(255, 165, 0):
        scolor[40] = "L"
    elif color==QColor(0, 0, 255):
        scolor[40] = "B"
    elif color==QColor(0, 128, 0):
        scolor[40] = "F"
    elif color==QColor(255, 255, 0):
        scolor[40] = "D"
    elif color==QColor(255, 255, 255):
        scolor[40] = "U"
def ob6():
    solver.ob6.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[42] = "R"
    elif color==QColor(255, 165, 0):
        scolor[42] = "L"
    elif color==QColor(0, 0, 255):
        scolor[42] = "B"
    elif color==QColor(0, 128, 0):
        scolor[42] = "F"
    elif color==QColor(255, 255, 0):
        scolor[42] = "D"
    elif color==QColor(255, 255, 255):
        scolor[42] = "U"
def ob7():
    solver.ob7.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[43] = "R"
    elif color==QColor(255, 165, 0):
        scolor[43] = "L"
    elif color==QColor(0, 0, 255):
        scolor[43] = "B"
    elif color==QColor(0, 128, 0):
        scolor[43] = "F"
    elif color==QColor(255, 255, 0):
        scolor[43] = "D"
    elif color==QColor(255, 255, 255):
        scolor[43] = "U"
def ob8():
    solver.ob8.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[44] = "R"
    elif color==QColor(255, 165, 0):
        scolor[44] = "L"
    elif color==QColor(0, 0, 255):
        scolor[44] = "B"
    elif color==QColor(0, 128, 0):
        scolor[44] = "F"
    elif color==QColor(255, 255, 0):
        scolor[44] = "D"
    elif color==QColor(255, 255, 255):
        scolor[44] = "U"
def ob9():
    solver.ob9.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[45] = "R"
    elif color==QColor(255, 165, 0):
        scolor[45] = "L"
    elif color==QColor(0, 0, 255):
        scolor[45] = "B"
    elif color==QColor(0, 128, 0):
        scolor[45] = "F"
    elif color==QColor(255, 255, 0):
        scolor[45] = "D"
    elif color==QColor(255, 255, 255):
        scolor[45] = "U"
#OB fuction
#BB fuction
def bb1():
    solver.bb1.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[46] = "R"
    elif color==QColor(255, 165, 0):
        scolor[46] = "L"
    elif color==QColor(0, 0, 255):
        scolor[46] = "B"
    elif color==QColor(0, 128, 0):
        scolor[46] = "F"
    elif color==QColor(255, 255, 0):
        scolor[46] = "D"
    elif color==QColor(255, 255, 255):
        scolor[46] = "U"
def bb2():
    solver.bb2.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[47] = "R"
    elif color==QColor(255, 165, 0):
        scolor[47] = "L"
    elif color==QColor(0, 0, 255):
        scolor[47] = "B"
    elif color==QColor(0, 128, 0):
        scolor[47] = "F"
    elif color==QColor(255, 255, 0):
        scolor[47] = "D"
    elif color==QColor(255, 255, 255):
        scolor[47] = "U"
def bb3():
    solver.bb3.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[48] = "R"
    elif color==QColor(255, 165, 0):
        scolor[48] = "L"
    elif color==QColor(0, 0, 255):
        scolor[48] = "B"
    elif color==QColor(0, 128, 0):
        scolor[48] = "F"
    elif color==QColor(255, 255, 0):
        scolor[48] = "D"
    elif color==QColor(255, 255, 255):
        scolor[48] = "U"
def bb4():
    solver.bb4.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[49] = "R"
    elif color==QColor(255, 165, 0):
        scolor[49] = "L"
    elif color==QColor(0, 0, 255):
        scolor[49] = "B"
    elif color==QColor(0, 128, 0):
        scolor[49] = "F"
    elif color==QColor(255, 255, 0):
        scolor[49] = "D"
    elif color==QColor(255, 255, 255):
        scolor[49] = "U"
def bb6():
    solver.bb6.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[51] = "R"
    elif color==QColor(255, 165, 0):
        scolor[51] = "L"
    elif color==QColor(0, 0, 255):
        scolor[51] = "B"
    elif color==QColor(0, 128, 0):
        scolor[51] = "F"
    elif color==QColor(255, 255, 0):
        scolor[51] = "D"
    elif color==QColor(255, 255, 255):
        scolor[51] = "U"
def bb7():
    solver.bb7.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[52] = "R"
    elif color==QColor(255, 165, 0):
        scolor[52] = "L"
    elif color==QColor(0, 0, 255):
        scolor[52] = "B"
    elif color==QColor(0, 128, 0):
        scolor[52] = "F"
    elif color==QColor(255, 255, 0):
        scolor[52] = "D"
    elif color==QColor(255, 255, 255):
        scolor[52] = "U"
def bb8():
    solver.bb8.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[53] = "R"
    elif color==QColor(255, 165, 0):
        scolor[53] = "L"
    elif color==QColor(0, 0, 255):
        scolor[53] = "B"
    elif color==QColor(0, 128, 0):
        scolor[53] = "F"
    elif color==QColor(255, 255, 0):
        scolor[53] = "D"
    elif color==QColor(255, 255, 255):
        scolor[53] = "U"
def bb9():
    solver.bb9.setStyleSheet("QPushButton {background-color: %s;}" % color.name())
    global scolor
    if color==QColor(255, 0, 0):
        scolor[54] = "R"
    elif color==QColor(255, 165, 0):
        scolor[54] = "L"
    elif color==QColor(0, 0, 255):
        scolor[54] = "B"
    elif color==QColor(0, 128, 0):
        scolor[54] = "F"
    elif color==QColor(255, 255, 0):
        scolor[54] = "D"
    elif color==QColor(255, 255, 255):
        scolor[54] = "U"
#BB fuction

app = QtWidgets.QApplication([])
solver=uic.loadUi("Solver.ui")

#Other Buttons fuctions
solver.red.clicked.connect(lambda: getcolor("red"))

solver.orange.clicked.connect(lambda: getcolor("orange"))

solver.blue.clicked.connect(lambda: getcolor("blue"))

solver.green.clicked.connect(lambda: getcolor("green"))

solver.yellow.clicked.connect(lambda: getcolor("yellow"))

solver.white.clicked.connect(lambda: getcolor("white"))

solver.reset.clicked.connect(reset)

solver.solve.clicked.connect(solve)

solver.label.setStyleSheet(" QLabel {background-image: url(back.png);}")

#Click WB
solver.wb1.clicked.connect(wb1)
solver.wb2.clicked.connect(wb2)
solver.wb3.clicked.connect(wb3)
solver.wb4.clicked.connect(wb4)
solver.wb6.clicked.connect(wb6)
solver.wb7.clicked.connect(wb7)
solver.wb8.clicked.connect(wb8)
solver.wb9.clicked.connect(wb9)
#Click YB
solver.yb1.clicked.connect(yb1)
solver.yb2.clicked.connect(yb2)
solver.yb3.clicked.connect(yb3)
solver.yb4.clicked.connect(yb4)
solver.yb6.clicked.connect(yb6)
solver.yb7.clicked.connect(yb7)
solver.yb8.clicked.connect(yb8)
solver.yb9.clicked.connect(yb9)
#Click RB
solver.rb1.clicked.connect(rb1)
solver.rb2.clicked.connect(rb2)
solver.rb3.clicked.connect(rb3)
solver.rb4.clicked.connect(rb4)
solver.rb6.clicked.connect(rb6)
solver.rb7.clicked.connect(rb7)
solver.rb8.clicked.connect(rb8)
solver.rb9.clicked.connect(rb9)
#Click OB
solver.ob1.clicked.connect(ob1)
solver.ob2.clicked.connect(ob2)
solver.ob3.clicked.connect(ob3)
solver.ob4.clicked.connect(ob4)
solver.ob6.clicked.connect(ob6)
solver.ob7.clicked.connect(ob7)
solver.ob8.clicked.connect(ob8)
solver.ob9.clicked.connect(ob9)
#Click BB
solver.bb1.clicked.connect(bb1)
solver.bb2.clicked.connect(bb2)
solver.bb3.clicked.connect(bb3)
solver.bb4.clicked.connect(bb4)
solver.bb6.clicked.connect(bb6)
solver.bb7.clicked.connect(bb7)
solver.bb8.clicked.connect(bb8)
solver.bb9.clicked.connect(bb9)
#Click GB
solver.gb1.clicked.connect(gb1)
solver.gb2.clicked.connect(gb2)
solver.gb3.clicked.connect(gb3)
solver.gb4.clicked.connect(gb4)
solver.gb6.clicked.connect(gb6)
solver.gb7.clicked.connect(gb7)
solver.gb8.clicked.connect(gb8)
solver.gb9.clicked.connect(gb9)

solver.show()
app.exec()
