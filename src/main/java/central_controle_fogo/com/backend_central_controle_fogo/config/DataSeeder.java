//package central_controle_fogo.com.backend_central_controle_fogo.config;
//
//import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceSubType;
//import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceType;
//
//import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceSubTypeRepository;
//import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceTypeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class DataSeeder implements CommandLineRunner {
//
//    @Autowired
//    private OccurrenceTypeRepository typeRepository;
//
//    @Autowired
//    private OccurrenceSubTypeRepository subTypeRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (typeRepository.count() == 0) {
//            seedData();
//        }
//    }
//
//    private void seedData() {
//        // Tipos Principais
//        OccurrenceType tipoIncendio = typeRepository.save(new OccurrenceType("Incêndio"));
//        OccurrenceType tipoResgate = typeRepository.save(new OccurrenceType("Atendimento Pré-Hospitalar (APH) ou Resgate"));
//        OccurrenceType tipoSalvamento = typeRepository.save(new OccurrenceType("Atendimento Pré-Hospitalar (APH) ou Resgate"));
//        OccurrenceType tipoDefesaCivil = typeRepository.save(new OccurrenceType("Ocorrências que afetam a comunidade ou envolvem riscos estruturais."));
//        OccurrenceType tipoProdutosPerigosos = typeRepository.save(new OccurrenceType("tendimentos que envolvem substâncias químicas, biológicas, radiológicas ou nucleares (QBRN)."));
//
//        //  SubTipos para Incêndio
//        subTypeRepository.saveAll(Arrays.asList(
//                new OccurrenceSubType("Incêndio Florestal", tipoIncendio),
//                new OccurrenceSubType("Incêndio Urbano", tipoIncendio),
//                new OccurrenceSubType("Incêndio em Industiral", tipoIncendio),
//                new OccurrenceSubType("Incêndio em Veicular", tipoIncendio)
//
//        ));
//
//        //  SubTipos para Resgate
//        subTypeRepository.saveAll(Arrays.asList(
//                new OccurrenceSubType("Traumas e Acidentes", tipoResgate),
//                new OccurrenceSubType("Emergência Clinica", tipoResgate),
//                new OccurrenceSubType("Outras Emergências Médicas", tipoResgate)
//        ));
//
//        //  SubTipos para Salvamento
//        subTypeRepository.saveAll(Arrays.asList(
//                new OccurrenceSubType("Salvamento Terrestre", tipoSalvamento),
//
//                new OccurrenceSubType("Salvamento Aquático", tipoSalvamento),
//                new OccurrenceSubType("Salvamento em Altura", tipoSalvamento),
//                new OccurrenceSubType("Salvamento de Animal", tipoSalvamento)
//        ));
//        //  SubTipos para DefesaCivil
//        subTypeRepository.saveAll(Arrays.asList(
//                new OccurrenceSubType("Corte ou Poda de Árvore", tipoDefesaCivil),
//
//                new OccurrenceSubType("Riscos Estruturais", tipoDefesaCivil),
//                new OccurrenceSubType("Eventos Climáticos", tipoDefesaCivil),
//                new OccurrenceSubType("Prevenção", tipoDefesaCivil)
//        ));
//        //  SubTipos para ProdutosPerigosos
//        subTypeRepository.saveAll(Arrays.asList(
//                new OccurrenceSubType("Vazamento de Gás", tipoSalvamento),
//
//                new OccurrenceSubType("Vazamento de combustível em acidentes", tipoProdutosPerigosos),
//                new OccurrenceSubType("cidentes com transporte de produtos químicos", tipoProdutosPerigosos),
//                new OccurrenceSubType("Contenção de derramamento de produtos perigosos", tipoProdutosPerigosos)
//        ));
//    }
//}