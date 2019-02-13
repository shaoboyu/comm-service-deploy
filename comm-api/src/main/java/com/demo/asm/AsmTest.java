package com.demo.asm;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 要想驾驭ASM，先要了解一下JAVA的CLASS文件格式。JAVA的CLASS文件通常是树型结构。根节点包含以下元素：
 *
 * ConstantPool：符号表；
 * FieldInfo：类中的成员变量信息；
 * MethodInfo：类中的方法描述；
 * Attribute：可选的附加节点。
 *       FieldInfo节点包含成员变量的名称，诸如public,private,static等的标志。
 *
 *       ConstantValue属性用来存储静态的不变的成员变量的值。
 *
 *       Deprecated和Synthetic被用来标记一个成员变量是不被推荐的或由编译器生成的。
 *
 *       MethodInfo节点包含方法的名称，参数的类型和和它的返回值，方法是公有的，私有的或静态的等标志。
 *
 *       MethodInfo包含可选的附加属性，其中最重要的是Code属性，它包含非抽象的方法的代码。
 *
 *       Exceptions属性包含方法将抛出的Exception的名称。
 *
 *       Deprecated和Synthetic属性的信息同上面的FieldInfo的定义一样。
 *
 *       根节点的可选属性有SourceFile，InnerClasses和Deprecated。
 *
 *       SourceFile用来存储被编译成字节码的源代码文件的原始名称;
 *
 *       InnerClasses存储内部类的信息。由于这些属性的存在，java 的类格式是可以扩展的，也就是说可以在一个class中 附加一些非标准的属性, java虚拟机会忽略这些不可识别的属性，正常的加载这个class。
 *
 *       ConstantPool是一个由数字或字符串常量的索引组成的队列，或由此类的树的其他节点引用的，由其他对象创建的被引用常量的索引组成的队列。这个表的目标是为了减少冗余。例如，FieldInfo节点不包含节点的名称，只包含它在这一表中的索引。同样的，GETFIELD和PUTFIELD不直接包含成员变量的名称，只包含名称的索引。
 *
 * @author: yushaobo
 * @create: 19-1-28
 **/
public class AsmTest {

    public static void main(String[] args) throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/demo/asm/AsmTest";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null,
                "java/lang/Object", null);


        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
                "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello",
                "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        File file = new File("/tmp/HelloWorld.class");
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();
    }
}
