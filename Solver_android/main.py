from kivy.config import Config
Config.set('graphics', 'resizable', '0') #0 being off 1 being on as in true/false
Config.set('graphics', 'width', '800')
Config.set('graphics', 'height', '512')
Config.set('kivy','window_icon','icon.ico')
from kivy.app import App
from kivy.uix.label import Label
from kivy.uix.gridlayout import GridLayout
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.relativelayout import RelativeLayout
from kivy.uix.label import Label
from kivy.uix.image import Image
import kociemba

colour=[255,225,255,1]
scolor=[None,'U','U','U','U','U','U','U','U','U','R','R','R','R','R','R','R','R','R','F','F','F','F','F','F','F','F','F','D','D','D','D','D','D','D','D','D','L','L','L','L','L','L','L','L','L','B','B','B','B','B','B','B','B','B',None]
scramble=""
condition=""

red=[255,0,0,1]
green=[0,128,0,1]
magenta=[255,0,255,1]
blue=[0,0,255,1]
yellow=[255,255,0,1]
white=[255,225,255,1]


class Pattern(RelativeLayout):
	
	def Solve(self):
		global scramble
		scramble="".join(scolor[1:55])
		condition=""
		try:
			condition = kociemba.solve(scramble)
		except:
			pass

		if condition.strip():
			self.ids['scramble'].text=condition
		elif condition is '':
			self.ids['scramble'].text="Invalid pattern try again click RESET"
	
	def GetColor(self,x):
		
		global colour
		if x =="red":
			colour=red
		elif x =="magenta":
			colour=magenta
		elif x =="blue":
			colour=blue
		elif x =="green":
			colour=green
		elif x =="yellow":
			colour=yellow
		elif x =="white":
			colour=white
			
	def ChangeColor(self,a,b, *args):
		self.ids[a].background_color = colour
		global scolor
		if colour==red:
			scolor[b] = "R"
		elif colour==magenta:
			scolor[b] = "L"
		elif colour==blue:
			scolor[b] = "B"
		elif colour==green:
			scolor[b] = "F"
		elif colour==yellow:
			scolor[b] = "D"
		elif colour==white:
			scolor[b] = "U"
		
	def Reset(self,*args):
	
		self.ids['wb1'].background_color=white
		self.ids['wb2'].background_color=white
		self.ids['wb3'].background_color=white
		self.ids['wb4'].background_color=white
		self.ids['wb5'].background_color=white
		self.ids['wb6'].background_color=white
		self.ids['wb7'].background_color=white
		self.ids['wb8'].background_color=white
		self.ids['wb9'].background_color=white
		
		self.ids['yb1'].background_color=yellow
		self.ids['yb2'].background_color=yellow
		self.ids['yb3'].background_color=yellow
		self.ids['yb4'].background_color=yellow
		self.ids['yb5'].background_color=yellow
		self.ids['yb6'].background_color=yellow
		self.ids['yb7'].background_color=yellow
		self.ids['yb8'].background_color=yellow
		self.ids['yb9'].background_color=yellow
		
		self.ids['gb1'].background_color=green
		self.ids['gb2'].background_color=green
		self.ids['gb3'].background_color=green
		self.ids['gb4'].background_color=green
		self.ids['gb5'].background_color=green
		self.ids['gb6'].background_color=green
		self.ids['gb7'].background_color=green
		self.ids['gb8'].background_color=green
		self.ids['gb9'].background_color=green
		
		self.ids['rb1'].background_color=red
		self.ids['rb2'].background_color=red
		self.ids['rb3'].background_color=red
		self.ids['rb4'].background_color=red
		self.ids['rb5'].background_color=red
		self.ids['rb6'].background_color=red
		self.ids['rb7'].background_color=red
		self.ids['rb8'].background_color=red
		self.ids['rb9'].background_color=red
		
		self.ids['bb1'].background_color=blue
		self.ids['bb2'].background_color=blue
		self.ids['bb3'].background_color=blue
		self.ids['bb4'].background_color=blue
		self.ids['bb5'].background_color=blue
		self.ids['bb6'].background_color=blue
		self.ids['bb7'].background_color=blue
		self.ids['bb8'].background_color=blue
		self.ids['bb9'].background_color=blue
		
		self.ids['ob1'].background_color=magenta
		self.ids['ob2'].background_color=magenta
		self.ids['ob3'].background_color=magenta
		self.ids['ob4'].background_color=magenta
		self.ids['ob5'].background_color=magenta
		self.ids['ob6'].background_color=magenta
		self.ids['ob7'].background_color=magenta
		self.ids['ob8'].background_color=magenta
		self.ids['ob9'].background_color=magenta
		
		self.ids['scramble'].text=""
		
		global scolor
		
		scolor=[None,'U','U','U','U','U','U','U','U','U','R','R','R','R','R','R','R','R','R','F','F','F','F','F','F','F','F','F','D','D','D','D','D','D','D','D','D','L','L','L','L','L','L','L','L','L','B','B','B','B','B','B','B','B','B',None]
		
		
		

class SimpleKivy2(App):
	def build(self):
		self.title = 'VivaCubeSolver'
		return Pattern()
		

if __name__ == "__main__":
    SimpleKivy2().run()