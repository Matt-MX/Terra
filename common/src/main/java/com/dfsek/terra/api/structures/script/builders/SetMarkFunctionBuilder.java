package com.dfsek.terra.api.structures.script.builders;

import com.dfsek.terra.api.structures.parser.exceptions.ParseException;
import com.dfsek.terra.api.structures.parser.lang.Returnable;
import com.dfsek.terra.api.structures.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.api.structures.script.functions.SetMarkFunction;
import com.dfsek.terra.api.structures.tokenizer.Position;

import java.util.List;

public class SetMarkFunctionBuilder implements FunctionBuilder<SetMarkFunction> {

    public SetMarkFunctionBuilder() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public SetMarkFunction build(List<Returnable<?>> argumentList, Position position) throws ParseException {
        return new SetMarkFunction((Returnable<Number>) argumentList.get(0), (Returnable<Number>) argumentList.get(1), (Returnable<Number>) argumentList.get(2), (Returnable<String>) argumentList.get(3), position);
    }

    @Override
    public int argNumber() {
        return 4;
    }

    @Override
    public Returnable.ReturnType getArgument(int position) {
        return switch(position) {
            case 0, 1, 2 -> Returnable.ReturnType.NUMBER;
            case 3 -> Returnable.ReturnType.STRING;
            default -> null;
        };
    }
}
