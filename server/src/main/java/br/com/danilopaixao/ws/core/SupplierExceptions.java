package br.com.danilopaixao.ws.core;

import java.io.Serializable;
import java.util.function.Supplier;

public final class SupplierExceptions {
	
	private SupplierExceptions() {
		throw new AssertionError();
	}

	public static Supplier<ResourceNotFoundException> supplierResourceNotFoundError(final String name, final Serializable id) {
		return () -> new ResourceNotFoundException(name, id);
	}
	
	public static Supplier<ResourceNotFoundException> supplierResourceNotFoundError(final String name, final String id) {
		return () -> new ResourceNotFoundException(name, id);
	}

}
