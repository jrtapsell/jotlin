fun R(input: String) = Regex(input)
fun c(input: String) = input.groupingBy { it }.eachCount()
fun String.r(regex: Regex, text: String) = replace(regex, text)
fun String.r(find: String, replace: String) = replace(find, replace)