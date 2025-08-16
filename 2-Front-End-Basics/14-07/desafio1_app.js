const vowel = ["a", "e", "i", "o", "u", "y"]

const word = "may"

for (let i = 0; i < word.length; i++) {
    const char = word[i];

    if (vowel.includes(char)) {
        // console.log(char);
        const prefix = word.slice(0, i)
        const stem = word.slice(i)

        // console.log(prefix);
        // console.log(stem);
        
        const translatedWord = stem + prefix + "ay"
        console.log(translatedWord);
        
        break
    }
}