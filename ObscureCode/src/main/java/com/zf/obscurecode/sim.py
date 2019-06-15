#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os,sys,re,ctypes

STD_OUTPUT_HANDLE = -11
FOREGROUND_BLUE = 0x09 # blue.
FOREGROUND_GREEN = 0x0a # green.
FOREGROUND_RED = 0x0c # red.
FOREGROUND_WHITE = 0x0f # white.

# get handle
std_out_handle = ctypes.windll.kernel32.GetStdHandle(STD_OUTPUT_HANDLE)

def set_cmd_text_color(color, handle=std_out_handle):
    Bool = ctypes.windll.kernel32.SetConsoleTextAttribute(handle, color)
    return Bool

#red
def printRed(mess):
    set_cmd_text_color(FOREGROUND_RED)
    sys.stdout.write(mess)
    resetColor()
	
#white
def printWhite(mess):
    set_cmd_text_color(FOREGROUND_WHITE)
    sys.stdout.write(mess)
    resetColor()
	
#reset white
def resetColor():
    set_cmd_text_color(FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE)

path_new = sys.argv[1]
path_old = sys.argv[2]

p = os.popen(r'sim_java -peu -S -R %s "|" %s' %(path_new,path_old))
result = p.read()
printWhite('\n')
printWhite(result)

p = os.popen(r'sim_java -peu -S -R -t 35 %s "|" %s' %(path_new,path_old))
filter_result = p.read()
regex = r"consists for [0-9]+"
search_result = re.search(regex, filter_result, re.M|re.I)
if search_result:
   printRed('\n')
   printRed('警告：以下相似度大于35%的结果需要处理')
   printRed('\n')
   printRed(filter_result)
else:
   printWhite('\n')
   printWhite('注意：没有检测到相识度大于35%的结果')   



'''
regex = r"consists for [0-9]+"
matches = re.finditer(regex, str, re.MULTILINE)
list = []
for matchNum, match in enumerate(matches, start=1):
    str = re.findall(r"\d+",match.group())[0]
    list.append(float(str))
print('percent:', sum(list)/len(list)) 
'''
   