/*
 * Copyright (c) 2020-2024 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.tokenizer.exceptions;

import java.io.Serial;

import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public class EOFException extends TokenizerException {

    @Serial
    private static final long serialVersionUID = 3980047409902809440L;

    public EOFException(String message, Position position) {
        super(message, position);
    }

    public EOFException(String message, Position position, Throwable cause) {
        super(message, position, cause);
    }
}
