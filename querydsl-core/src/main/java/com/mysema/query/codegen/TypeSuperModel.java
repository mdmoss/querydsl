/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.codegen;

import java.io.IOException;

import javax.annotation.Nullable;

/**
 * TypeSuperModel is a TypeModel for type variables and wildcard types
 * 
 * @author tiwe
 *
 */
// TODO : take varName into account
public class TypeSuperModel extends TypeModelAdapter{
    
    private static final TypeModel objectModel = new ClassTypeModel(TypeCategory.SIMPLE, Object.class);
    
    private final TypeModel superModel;
    
    @Nullable
    private final String varName;
    
    public TypeSuperModel(String varName, TypeModel typeModel) {
        super(objectModel);        
        this.superModel = typeModel;
        this.varName = varName;
    }

    public TypeSuperModel(TypeModel typeModel) {
        super(objectModel);
        this.superModel = typeModel;
        this.varName = null;
    }
    
    @Override
    public void appendLocalGenericName(TypeModel context, Appendable builder, boolean asArgType) throws IOException {
        if (!asArgType){
            builder.append("? super ");
            superModel.appendLocalGenericName(context, builder, true);
        }else{
            super.appendLocalGenericName(context, builder, asArgType);
        }    
    }

    public String getVarName(){
        return varName;
    }
}
