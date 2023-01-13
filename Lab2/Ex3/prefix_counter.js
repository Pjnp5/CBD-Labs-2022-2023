prefix_counter = function(){

    return db.phones.aggregate([{$group : {_id : "$components.prefix", total:{$sum:1}}}])

}

/*
populatePhones adds phone numbers this way:
_id : num
components: {
    country: country, components.country
    prefix: prefix, components.prefix
    number: i components.number
},
display: fullNumber

How to run:
test> prefix_counter()

Result(in my case):
[
  { _id: 232, total: 33483 },
  { _id: 22, total: 33183 },
  { _id: 21, total: 32964 },
  { _id: 233, total: 33412 },
  { _id: 234, total: 33486 },
  { _id: 231, total: 33472 }
]
*/