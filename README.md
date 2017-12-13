# About algorithm
This code found the best set of clothes in wardrobe. Algorithm uses 4 parametrs: weather, target, comfortable and colors. It is genetic algorithm. We create generation and find the best solve. If algorithm doesn't give new the best set for 10 generationm, current maximum is the best set for wear.
# How use
Create and fill list of clothes
```kotlin
val list = ListClothes()
val clothes = Footwear()
list.add(clothes)
```
Use genetic algorithm for get the best set for wear
```kotlin
val temperature = Temperature(-1.0, 4.0)
temperature.isRain = false
val res = geneticSolve(list, temperature)
```
Test example of use https://github.com/kam123ua/Wardrober-Genetic-Algorithm/blob/master/src/test/java/Main.kt


# Plans
I want to create android application. Person load photo of clothes and give information about it. After that wardrobe is full, people can get the best set for wear.
