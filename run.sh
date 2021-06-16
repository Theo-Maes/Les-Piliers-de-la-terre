#!/bin/sh

#  run.sh
#  run
#
#  Created by Thomas Chevalier on 16/06/2021.
#  

javac @compile.list -d target/ 2> dev/null

if [ -z $1 ] || [ $1 = "GUI" ]; then
	java -cp target/ equipe_26.Controleur &
elif [ $1 = "CUI" ]; then
	java -cp target/ equipe_26.Controleur CUI
fi
