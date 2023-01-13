even_numbers = function(){
    const numbers = db.phones.find({}, {"display": 1, "_id": 0}).toArray();
    let oddys = [];
    let ctr;
    let n;
    for (let i = 0; i < numbers.length; i++) {
        n = numbers[i]["display"].slice(5);
        ctr = true
        for (let letter = 0; letter < n.length; letter++) {
            if (n[letter] % 2 === 1) {
                ctr = false
                break;
            } else {}
        }
        if (ctr === true) {
            oddys.push(n)
        }
    }
    return oddys;
}

// returns all the phone numbers who only have even numbers

/*
How to run (already loaded):
even_numbers()

Result (in my case):
[
  '220000002', '220000004', '220000024', '220000042', '220000066',
  '220000220', '220000226', '220000228', '220000240', '220000248',
  '220000266', '220000424', '220000426', '220000466', '220000482',
  '220000488', '220000602', '220000640', '220000642', '220000648',
  '220000808', '220000822', '220000828', '220000880', '220002024',
  '220002044', '220002048', '220002064', '220002082', '220002208',
  '220002280', '220002282', '220002444', '220002600', '220002642',
  '220002644', '220002666', '220002668', '220002682', '220002686',
  '220002840', '220002844', '220002864', '220002866', '220002880',
  '220004000', '220004002', '220004006', '220004040', '220004080',
  '220004082', '220004084', '220004204', '220004220', '220004224',
  '220004242', '220004288', '220004462', '220004464', '220004484',
  '220004488', '220004606', '220004608', '220004648', '220004682',
  '220004802', '220004822', '220004844', '220004846', '220004862',
  '220004880', '220006022', '220006028', '220006082', '220006222',
  '220006226', '220006404', '220006424', '220006480', '220006604',
  '220006628', '220006660', '220006680', '220006688', '220006804',
  '220006826', '220006864', '220006884', '220008000', '220008006',
  '220008028', '220008244', '220008260', '220008268', '220008440',
  '220008460', '220008464', '220008600', '220008604', '220008620',
  ... 440 more items
]
*/


// Returns all the numbers that are palindromes
palindrome = function (){
    const numbers = db.phones.find({}, {"display": 1, "_id": 0}).toArray();
    let palindromes = [];
    let n;
    for (let i = 0; i < numbers.length; i++) {
        n = numbers[i]["display"].slice(5);
        if (equal_digits(n)) {
            palindromes.push(n)
        }
    }
    return palindromes;
}


// Recursive function that checks if a word is a palindrome, created it to make the code cleaner
equal_digits = function (n){
    if(n.length === 1){
        return true
    }
    else if (n[0] !== n[n.length -1]){
        return false
    }

    return equal_digits(n.slice(1,-1))
}

// Returns all the numbers who all digits are different
all_digits_different = function(){
    const numbers = db.phones.find({}, {"display": 1, "_id": 0}).toArray();
    const diff_numbers = [];
    let n;
    let array;
    for (let i = 0; i < numbers.length; i++) {
        n = numbers[i]["display"].slice(5);
        let control = true;
        array = []
        for (let letter = 0; letter < n.length; letter++) {
            if (array.includes(n[letter])) {
                control = false;
                break;
            } else {
                array.push(n[letter])
            }
        }
        if (control === true) {
            diff_numbers.push(n)
        }
    }
    return diff_numbers;
}
