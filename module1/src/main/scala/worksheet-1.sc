val myList = List("Scala", "Spark", "mimics", "collection")
val mapped = myList.map(s => s.toUpperCase())
val flatMapped = myList.flatMap(s => s.toUpperCase())
val flatMappedFilter = myList.flatMap { s =>
  val filters = List("mimics", "collection")
  if (filters.contains(s))
    None
  else
    Some(s)
}

def sayHello(name: String)(whoAreYou: () => String) = {
  s"Hello $name! My name is ${whoAreYou}"
}

val faster = sayHello("test")({() => "Anonymous"})
print(faster)

def sayHelloImplicit(name: String)(implicit myself: String) = {
  s"Hello $name. My name is $myself"
}

implicit val myString = "Este yo..."
sayHelloImplicit("A name")


abstract class Person(fname: String, lname: String) {
  def fullname = s"$fname - $lname"
}

case class Student(fname: String, lname: String, id: Int) extends Person(fname, lname) {

}

val me = Student("Jose", "Corbacho", 23)

def getFullID[T <: Person](something: T) = {
  something match {
    case Student(fname, lname, id) => s"$fname - $lname - $id"
    case p: Person => p.fullname
  }
}

getFullID(me)


// Implicit class. Acts as a decorator
implicit class stringUtils(aString: String) {
  def scalaWordCount() = {
    val split = aString.split("\\s+")
    val grouped = split.groupBy(word => word)
    val countPerKey = grouped.mapValues(group => group.length)
    countPerKey
  }
}

// Implicit conversion. Allows transforming a class into another
// Implicit conversion must be in the same scope
// Spark uses this a lot: converting a RDD to another
"This is a string that can be counted. This is it".scalaWordCount()

