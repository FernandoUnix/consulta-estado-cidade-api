package com.consulta.estado.cidade.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface IFileConvertService {
	OutputStream convertert(OutputStream outputStream, List<?> list) throws IOException;
}
