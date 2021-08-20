for ((fileSize = 100; fileSize<=100000; fileSize*=10))
do
    for (( width=3; width<=21; width+=3 ))
    do
        for i in {1..30}
        do
            make run-par in=sampleInput$fileSize width=$width cutoff=$(($fileSize/4)) out=$fileSize-$width-out.txt
        done
    done
done