package com.dfsek.terra.api.structures.script.builders;

import com.dfsek.terra.api.structures.parser.lang.Returnable;
import com.dfsek.terra.api.structures.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.api.structures.script.functions.CheckBlockFunction;
import com.dfsek.terra.api.structures.tokenizer.Position;

import java.util.List;

public class CheckBlockFunctionBuilder implements FunctionBuilder<CheckBlockFunction> {
    @SuppressWarnings("unchecked")
    @Override
    public CheckBlockFunction build(List<Returnable<?>> argumentList, Position position) {
        return new CheckBlockFunction((Returnable<Number>) argumentList.get(0), (Returnable<Number>) argumentList.get(1), (Returnable<Number>) argumentList.get(2), position);
    }

    @Override
    public int argNumber() {
        return 3;
    }

    @Override
    public Returnable.ReturnType getArgument(int position) {
        return switch(position) {
            case 0, 1, 2 -> Returnable.ReturnType.NUMBER;
            default -> null;
        };
    }
}
