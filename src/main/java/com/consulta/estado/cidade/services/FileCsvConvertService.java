package com.consulta.estado.cidade.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Service;
import com.consulta.estado.cidade.utils.Log;
import com.consulta.estado.cidade.utils.ObjectListToCSV;

@Service
public class FileCsvConvertService implements IFileConvertService {

	public OutputStream convertert(OutputStream outputStream, List<?> list) throws IOException {
		
		try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {

			String csv = ObjectListToCSV.convertListToCSV(list);
			
			writer.write(csv);
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
