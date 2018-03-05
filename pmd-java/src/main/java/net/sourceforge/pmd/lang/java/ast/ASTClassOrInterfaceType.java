/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTClassOrInterfaceType.java */

package net.sourceforge.pmd.lang.java.ast;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.ast.Node;

public class ASTClassOrInterfaceType extends AbstractJavaTypeNode {
    public ASTClassOrInterfaceType(int id) {
        super(id);
    }

    public ASTClassOrInterfaceType(JavaParser p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Checks whether the type this node is referring to is declared within the
     * same compilation unit - either a class/interface or a enum type. You want
     * to check this, if {@link #getType()} is null.
     *
     * @return <code>true</code> if this node referencing a type in the same
     *         compilation unit, <code>false</code> otherwise.
     */
    public boolean isReferenceToClassSameCompilationUnit() {
        ASTCompilationUnit root = getFirstParentOfType(ASTCompilationUnit.class);
        List<ASTClassOrInterfaceDeclaration> classes = new ArrayList<>();
        root.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class, classes, true);
        for (ASTClassOrInterfaceDeclaration c : classes) {
            if (c.hasImageEqualTo(getImage())) {
                return true;
            }
        }
        List<ASTEnumDeclaration> enums = new ArrayList<>();
        root.findDescendantsOfType(ASTEnumDeclaration.class, enums, true);
        for (ASTEnumDeclaration e : enums) {
            if (e.hasImageEqualTo(getImage())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnonymousClass() {
        return jjtGetParent().getFirstChildOfType(ASTClassOrInterfaceBody.class) != null;
    }

    public boolean isArray() {
        Node p = jjtGetParent();
        if (p instanceof ASTReferenceType) {
            return ((ASTReferenceType) p).isArray();
        }
        return false;
    }
    
    public int getArrayDepth() {
        Node p = jjtGetParent();
        if (p instanceof ASTReferenceType) {
            return ((ASTReferenceType) p).getArrayDepth();
        }
        return 0;
    }
}
