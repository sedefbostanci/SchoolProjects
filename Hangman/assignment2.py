import sys
word = str(sys.argv[1])
word_list= list(word)
a = sys.argv[2]
letter_list = a.split(",")
In_list = []
out_list = []
mod = 1
guessed = 5


revealStr = ""
for i in word:
    revealStr = revealStr + "-,"
reveal = revealStr[0:len(revealStr)-1]
c = reveal.split(",")

print("You have 5 guesses left")
print(c)
print("--------------------------------------------")


for i in letter_list:
    if mod == 1 :
        if i in word:
            if i in In_list :
                mod -= 1
                print("Guessed word: {}".format(i) ,"is used in IN mode.","The game turn into OUT mode")
                guessed -=1
                print("You have {} guesses left".format(guessed))
                if guessed <= 0:
                    print("You lost the game")
                    break
                print(c)
                print("--------------------------------------------")
            else:
                In_list.append(i)
                print("Guessed word: {}".format(i) , "You are in IN mode")
                print("You have {} guesses left".format(guessed))
                e= letter_list.index(i)
                indices= [y for y, x in enumerate(word) if x== letter_list[int(e)]]
                for s in indices:
                    c[s] = word[s]
                print(c)
                if word_list == c :
                    print("--------------------------------------------")
                    print("You won the game")
                    break
                print("--------------------------------------------")

        else:
            guessed -= 1
            print("Guessed word: {}".format(i), "The game turned into OUT mode")
            print("You have {}".format(guessed), "guesses left")
            print(c)
            print("--------------------------------------------")
            if guessed <= 0:
                print("You lost the game")
                break
            mod -= 1
    elif mod == 0 :
        if i in out_list:
            guessed -= 1
            print("Guessed word: {}".format(i),"is used in OUT mode." ,"The game turned into OUT mode")
            print("You have {} guesses left".format(guessed))
            print(c)
            print("--------------------------------------------")
            if guessed <= 0:
                print("You lost the game")
                break
            mod = 0
        elif i in word:
            print("Guessed word: {}".format(i) , "You are in OUT mode")
            guessed -= 1
            print("You have {} guesses left".format(guessed))
            print(c)
            print("--------------------------------------------")
            if guessed <= 0:
                print("You lost the game")
                break
            mod=0
        else:
            print("Guessed word: {}".format(i) ,"The game turn into IN mode")
            print("You have {} guesses left".format(guessed))
            print(c)
            print("--------------------------------------------")
            out_list.append(i)
            mod += 1
if guessed > 0 and word_list != c :
    print("You finished all letter.")
    print("You lost the game.")










