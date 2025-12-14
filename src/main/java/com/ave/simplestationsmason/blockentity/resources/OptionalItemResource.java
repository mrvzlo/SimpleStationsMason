package com.ave.simplestationsmason.blockentity.resources;

import com.ave.simplestationsmason.blockentity.handlers.BaseSidedItemHandler;

public class OptionalItemResource extends ItemResource {
    public OptionalItemResource(BaseSidedItemHandler inventory, int slot, int usage) {
        super(inventory, slot, usage);
    }

    @Override
    public int getRequired() {
        return 0;
    }
}
