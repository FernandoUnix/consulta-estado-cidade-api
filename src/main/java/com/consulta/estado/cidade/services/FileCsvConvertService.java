package com.consulta.estado.cidade.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.consulta.estado.cidade.utils.Log;

@Service
public class FileCsvConvertService implements IFileConvertService {

	@Override
	public OutputStream converter(OutputStream outputStream, List<Map<String, String>> list) throws IOException {
		List<String> headers = list.stream().flatMap(map -> map.keySet().stream()).distinct()
				.collect(Collectors.toList());

		Log.getLog().info("Inicio conversão do arquivo para .csv");
		
		try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {

			for (String string : headers) {
				writer.write(string);
				writer.write(",");
			}
			writer.write("\r\n");

			for (Map<String, String> lmap : list) {
				for (Entry<String, String> string2 : lmap.entrySet()) {
					writer.write(string2.getValue());
					writer.write(",");
				}
				writer.write("\r\n");
			}

			writer.flush();
			writer.close();

			Log.getLog().info("Fim conversão do arquivo para .csv");
			
			return outputStream;

		} catch (Exception e) {
			Log.getLog().fine("Erro ao converter para csv" + e.getMessage());
			throw new IOException("Erro ao converter para csv");
		}
	}

}
