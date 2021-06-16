@ECHO off

REM !/bin/bat

REM  run.bat
REM  run
REM
REM  Created by Thomas Chevalier on 16/06/2021.
REM  


javac @compile.list -d target/ 2> NUL

if !%1==!  (
    java -cp target/ equipe_26.Controleur
)
if "%1" EQU "GUI" (
    java -cp target/ equipe_26.Controleur
)
if "%1" EQU "CUI" (
    java -cp target/ equipe_26.Controleur CUI
)
