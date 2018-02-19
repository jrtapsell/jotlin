# Built-ins

## Any

- Any.i()

  Converts anything to an integer
  
      > "1".i() + "3".i()
      < 4
    
- Any.s()

  Converts anything to a string
  
      > 1.s()
      < "1"
  
## IO

- P(x)

  Prints out x followed by a newline
  
- P(x,y,z...)
  
  Prints out (x,y,z) as a list
  
## Iterables 
**(Iterable, Sequence and Array)**

- m(function)

  Shortening of map
  
- f(predicate) 

  Shortening of filter
  
- c()

  Counts all the items in the iterable

- j()

  Joins the items to a string
  
- g()

  Shortening of generateSequence
  
- _s()

  Converts to a sequence
  
- _i()

  Converts to an iterable
  
_ _a()

  Converts to an array
  
## Literals

- l(x,y,z...)

  Creates a mutable list of (x,y,z...)

- a(x,y,z...)

  Creates an array of (x,y,z...)
  
## String Utilities

- R()

  Creates a regex
  
- r()

  Replaces in strings
  
- c()

  Counts like the one in iterables