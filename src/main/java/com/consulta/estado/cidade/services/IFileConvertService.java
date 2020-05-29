package com.consulta.estado.cidade.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface IFileConvertService {
	OutputStream converter(OutputStream outputStream, List<Map<String, String>> list) throws IOException;
}
