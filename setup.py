import cx_Freeze
import sys

base = None

if sys.platform == 'win32':
    base ="Win32GUI"

executables=[cx_Freeze.Executable("solver.py",
            shortcutName="VivaCubeSolver",
            shortcutDir="DesktopFolder",base=base, icon="icon.ico")]

cx_Freeze.setup(
    name= "VivaCubeSolver",
    options={"builde.exe": {"include files":["back.png","icon.ico","solver.ui"]}},
    version="0.0.1",
    description="Rubiks cube solver",
    executables=executables
    )
