#!/bin/bash

 
for file in `ls *.jfif`
do
    newfile=`echo $file | sed 's/jfif/jpg/'`
    convert $file $newfile
done