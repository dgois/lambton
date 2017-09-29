//: Playground - noun: a place where people can play

print("For structs")
struct s {
    var x = 100
}

var s1 = s()
print("s1 (x) \(s1.x)")
var s2 = s1
print("s2 (x) \(s2.x)")
s1.x = 200
print("s1 (x) \(s1.x)")
print("s2 (x) \(s2.x)")

print("For classes")
class c {
    var x = 100
}


var c1 = c()
print("c1 (x) \(c1.x)")
var c2 = c1
print("c2 (x) \(c2.x)")
c1.x = 200
print("c1 (x) \(c1.x)")
print("c2 (x) \(c2.x)")


func test(x :c) -> c {
    print(x.x)
    let y = c()
    y.x = 255
    y.x = x.x
    return y
}

print(c1.x)
test(x: c1)
print(c1.x)

print("-------------------------")

class f {
    var c0 = c()
    var y = 1000
}

var f1 = f()
print("f1 (y) \(f1.y)")
print("f1 (c0.x) \(f1.c0.x)")

var f2 = f()
f2.y = 2000
f2.c0.x = 200
print("f2 (y) \(f2.y)")
print("f2 (c0.x) \(f2.c0.x)")

f2.c0.x = f1.c0.x
print("*************************")
print("f1 (c0.x) \(f1.c0.x)")
print("f2 (c0.x) \(f2.c0.x)")

f1.c0.x = 300
print("*************************")
print("f1 (c0.x) \(f1.c0.x)")
print("f2 (c0.x) \(f2.c0.x)")



