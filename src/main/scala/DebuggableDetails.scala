
case class Debuggable(value: Int, msg: String) {

    def map(f: Int => Int): Debuggable = {
        println("\n>>>> entered map  >>>>")
        println(s"map: value: ${value}")
        println(s"map: msg: (${msg})")
        val nextValue = f(value)   //Int
        // there is no `nextValue.msg`
        println(s"map: nextValue: ${nextValue}")
        println("<<<< leaving map  <<<<\n")
        Debuggable(nextValue, msg)
    }

    def flatMap(f: Int => Debuggable): Debuggable = {
        println("\n>>>> entered fmap >>>>")
        println(s"fmap: value: ${value}")
        println(s"fmap: msg: (${msg})")
        val nextValue = f(value)
        println(s"fmap: msg: (${msg})")
        println(s"fmap: next val: ${nextValue.value}")
        println(s"fmap: next msg: \n(${nextValue.msg})")
        println("<<<< leaving fmap <<<<\n")
        Debuggable(nextValue.value, msg + "\n" + nextValue.msg)
    }
}

object DebuggableTestDetails extends App {

    def f(a: Int): Debuggable = {
        println(s"\n[f: a = $a]")
        val result = a * 2
        Debuggable(result, s"f: input: $a, result: $result")
    }

    def g(a: Int): Debuggable = {
        println(s"\n[g: a = $a]")
        val result = a * 3
        Debuggable(result, s"g: input: $a, result: $result")
    }

    def h(a: Int): Debuggable = {
        println(s"\n[h: a = $a]")
        val result = a * 4
        Debuggable(result, s"h: input: $a, result: $result")
    }

    //I couldn't understand this. How can the 'for' extract an integer (e.g. fRes) from the State Monad?
    //by means of 'map'? Sorry to bother with these dummy questions but I have experienced only fors which
    //have worked with Lists :)
    val finalResult = for {
        fRes <- f(100)
        gRes <- g(fRes)
        hRes <- h(gRes)
    } yield hRes

    println("\n----- FINAL RESULT -----")
    println(s"final value: ${finalResult.value}")
    println(s"final msg:   \n${finalResult.msg}")

}




