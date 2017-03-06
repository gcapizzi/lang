package io.github.gcapizzi.lang.visitor;

import io.github.gcapizzi.lang.ast.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.objectweb.asm.Opcodes.*;

public class CompilerLangVisitor implements LangVisitor {
    private MethodVisitor currentMethod;

    @Override
    public void visit(IntegerLiteralNode integerLiteralNode) {
        currentMethod.visitTypeInsn(NEW, "io/github/gcapizzi/lang/model/IntegerLangObject");
        currentMethod.visitLdcInsn(integerLiteralNode.getValue());
        currentMethod.visitMethodInsn(INVOKESPECIAL,
                "\"io/github/gcapizzi/lang/model/IntegerLangObject\"",
                "<init>",
                "(I)V",
                false
        );
    }

    @Override
    public void visit(StringLiteralNode stringLiteralNode) {
        System.out.println("Visiting " + stringLiteralNode);

        currentMethod.visitTypeInsn(NEW, "io/github/gcapizzi/lang/model/StringLangObject");
        currentMethod.visitInsn(DUP);
        currentMethod.visitLdcInsn(stringLiteralNode.getValue());
        currentMethod.visitMethodInsn(INVOKESPECIAL,
                "io/github/gcapizzi/lang/model/StringLangObject",
                "<init>",
                "(Ljava/lang/String;)V",
                false
        );
    }

    @Override
    public void visit(ConstantNode constantNode) {
    }

    @Override
    public void visit(MethodCallsNode methodCallsNode) {
        System.out.println("Visiting " + methodCallsNode);

        if (methodCallsNode.getTarget() instanceof ConstantNode) {
            ConstantNode target = (ConstantNode) methodCallsNode.getTarget();
            MethodCall methodCall = methodCallsNode.getMethodCalls().get(0);
            methodCall.getArguments().forEach(arg -> arg.evaluate(this));
            currentMethod.visitMethodInsn(INVOKESTATIC,
                    "io/github/gcapizzi/lang/" + target.getName(),
                    methodCall.getMethodName(),
                    "(Lio/github/gcapizzi/lang/model/StringLangObject;)V",
                    false);
            methodCallsNode.getMethodCalls().remove(0);
        } else {
            methodCallsNode.getTarget().evaluate(this);
            /*
            for (MethodCall methodCall : methodCallsNode.getMethodCalls()) {
                List<Node> arguments = methodCall.getArguments();
                arguments.forEach(arg -> arg.evaluate(this));
                List<LangObject> evaluatedArgs = popManyInOrder(arguments.size());
                push(pop().invokeMethod(methodCall.getMethodName(), evaluatedArgs));
            }
            */
        }
    }

    @Override
    public void visit(ProgramNode programNode) {
        ClassWriter mainClassWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        mainClassWriter.visit(V1_8,
                ACC_PUBLIC + ACC_SUPER,
                "io/github/gcapizzi/lang/LangTest",
                null,
                "java/lang/Object",
                null);

        currentMethod = mainClassWriter.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null
        );

        programNode.getStatements().forEach(s -> s.evaluate(this));

        currentMethod.visitInsn(RETURN);
        currentMethod.visitMaxs(0, 0);
        currentMethod.visitEnd();

        mainClassWriter.visitEnd();

        byte[] classBytes = mainClassWriter.toByteArray();

        try {
            Files.write(Paths.get("./target/classes/io/github/gcapizzi/lang/LangTest.class"), classBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
