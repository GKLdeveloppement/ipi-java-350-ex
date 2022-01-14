Performance des commerciaux
=====================
Created by Semih on 14/01/2022

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
## Scenario 1 : Calculer l'indicateur de performance d'un employé commercial ayant un CA null
* Soit un commercial appelé "John" "Wick" ayant pour matricule "C12345", un chiffre d'affaire de "" euros et avec un objectif de "7000" euros.
* Quand je calcule son indice de performance,
* J'obtiens une erreur avec le message suivant : "Le chiffre d'affaire traité ne peut être négatif ou null !".

## Scenario 2 : Calculer l'indicateur de performance d'un employé commercial ayant un CA négatif
* Soit un commercial appelé "John" "Wick" ayant pour matricule "C12345", un chiffre d'affaire de "-1700" euros et avec un objectif de "7000" euros.
* Quand je calcule son indice de performance,
* J'obtiens une erreur avec le message suivant : "Le chiffre d'affaire traité ne peut être négatif ou null !".

## Scenario 3 : Calculer l'indicateur de performance d'un employé commercial ayant un matricule incorect
* Soit un commercial appelé "John" "Wick" ayant pour matricule "T12345", un chiffre d'affaire de "4500" euros et avec un objectif de "7000" euros.
* Quand je calcule son indice de performance,
* J'obtiens une erreur avec le message suivant : "Le matricule ne peut être null et doit commencer par un C !".