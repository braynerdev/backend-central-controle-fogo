package central_controle_fogo.com.backend_central_controle_fogo.config;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.*;
import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import central_controle_fogo.com.backend_central_controle_fogo.model.vehicles.Vehicle;
import central_controle_fogo.com.backend_central_controle_fogo.repository.address.IAddressRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceSubTypeRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceTypeRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceUsersRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatentRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.vehicle.IVehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            IPatentRepository patentRepository,
            IBattalionRepository battalionRepository,
            IAddressRepository addressRepository,
            IRepositoryUser userRepository,
            IVehicleRepository vehicleRepository,
            OccurrenceTypeRepository occurrenceTypeRepository,
            OccurrenceSubTypeRepository occurrenceSubTypeRepository,
            OccurrenceRepository occurrenceRepository,
            OccurrenceUsersRepository occurrenceUsersRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (patentRepository.count() > 0) {
                System.out.println("Banco de dados já possui dados. Pulando inserção inicial.");
                return;
            }

            System.out.println("Iniciando inserção de dados iniciais...");

            // 1. Inserir Patentes (Patents)
            Patent soldado = new Patent();
            soldado.setName("Soldado");
            soldado = patentRepository.save(soldado);

            Patent cabo = new Patent();
            cabo.setName("Cabo");
            cabo = patentRepository.save(cabo);

            Patent sargento = new Patent();
            sargento.setName("Sargento");
            sargento = patentRepository.save(sargento);

            Patent tenente = new Patent();
            tenente.setName("Tenente");
            tenente = patentRepository.save(tenente);

            Patent capitao = new Patent();
            capitao.setName("Capitão");
            capitao = patentRepository.save(capitao);

            Patent major = new Patent();
            major.setName("Major");
            major = patentRepository.save(major);

            Patent coronel = new Patent();
            coronel.setName("Coronel");
            coronel = patentRepository.save(coronel);

            Patent tenente2 = new Patent();
            tenente2.setName("2º Tenente");
            tenente2 = patentRepository.save(tenente2);

            Patent subtenente = new Patent();
            subtenente.setName("Subtenente");
            subtenente = patentRepository.save(subtenente);

            Patent aspirante = new Patent();
            aspirante.setName("Aspirante");
            aspirante = patentRepository.save(aspirante);

            System.out.println("✓ Patentes inseridas: 10 registros");

            // 2. Inserir Endereços para os Batalhões
            Address addressBattalion1 = new Address(
                    "Rua dos Bombeiros",
                    100,
                    "Quartel Central",
                    "Centro",
                    "São Paulo",
                    "SP",
                    "01310000"
            );

            Address addressBattalion2 = new Address(
                    "Avenida Brigadeiro",
                    500,
                    "Próximo ao Shopping",
                    "Jardins",
                    "São Paulo",
                    "SP",
                    "01401000"
            );

            Address addressBattalion3 = new Address(
                    "Rua das Acácias",
                    250,
                    null,
                    "Vila Mariana",
                    "São Paulo",
                    "SP",
                    "04101000"
            );

            // 3. Inserir Batalhões (Battalions)
            Battalion battalion1 = new Battalion(
                    "1º Batalhão de Bombeiros",
                    "11987654321",
                    "batalhao1@bombeiros.sp.gov.br"
            );
            battalion1.setEndereco(addressBattalion1);
            battalion1 = battalionRepository.save(battalion1);

            Battalion battalion2 = new Battalion(
                    "2º Batalhão de Bombeiros",
                    "11987654322",
                    "batalhao2@bombeiros.sp.gov.br"
            );
            battalion2.setEndereco(addressBattalion2);
            battalion2 = battalionRepository.save(battalion2);

            Battalion battalion3 = new Battalion(
                    "3º Batalhão de Bombeiros",
                    "11987654323",
                    "batalhao3@bombeiros.sp.gov.br"
            );
            battalion3.setEndereco(addressBattalion3);
            battalion3 = battalionRepository.save(battalion3);

            // Batalhões adicionais (4 ao 10)
            Battalion battalion4 = new Battalion(
                    "4º Batalhão de Bombeiros",
                    "11987654324",
                    "batalhao4@bombeiros.sp.gov.br"
            );
            battalion4.setEndereco(new Address("Rua Itapeva", 300, "Edifício Principal", "Bela Vista", "São Paulo", "SP", "01332000"));
            battalion4 = battalionRepository.save(battalion4);

            Battalion battalion5 = new Battalion(
                    "5º Batalhão de Bombeiros",
                    "11987654325",
                    "batalhao5@bombeiros.sp.gov.br"
            );
            battalion5.setEndereco(new Address("Avenida Ibirapuera", 1500, null, "Moema", "São Paulo", "SP", "04029200"));
            battalion5 = battalionRepository.save(battalion5);

            Battalion battalion6 = new Battalion(
                    "6º Batalhão de Bombeiros",
                    "11987654326",
                    "batalhao6@bombeiros.sp.gov.br"
            );
            battalion6.setEndereco(new Address("Rua Vergueiro", 800, "Torre A", "Liberdade", "São Paulo", "SP", "01504000"));
            battalion6 = battalionRepository.save(battalion6);

            Battalion battalion7 = new Battalion(
                    "7º Batalhão de Bombeiros",
                    "11987654327",
                    "batalhao7@bombeiros.sp.gov.br"
            );
            battalion7.setEndereco(new Address("Avenida Morumbi", 2000, "Quartel Sul", "Morumbi", "São Paulo", "SP", "05699900"));
            battalion7 = battalionRepository.save(battalion7);

            Battalion battalion8 = new Battalion(
                    "8º Batalhão de Bombeiros",
                    "11987654328",
                    "batalhao8@bombeiros.sp.gov.br"
            );
            battalion8.setEndereco(new Address("Rua da Consolação", 2500, null, "Consolação", "São Paulo", "SP", "01302000"));
            battalion8 = battalionRepository.save(battalion8);

            Battalion battalion9 = new Battalion(
                    "9º Batalhão de Bombeiros",
                    "11987654329",
                    "batalhao9@bombeiros.sp.gov.br"
            );
            battalion9.setEndereco(new Address("Avenida Jabaquara", 1200, "Complexo Sul", "Saúde", "São Paulo", "SP", "04046200"));
            battalion9 = battalionRepository.save(battalion9);

            Battalion battalion10 = new Battalion(
                    "10º Batalhão de Bombeiros",
                    "11987654330",
                    "batalhao10@bombeiros.sp.gov.br"
            );
            battalion10.setEndereco(new Address("Rua Guaianases", 600, null, "Sacomã", "São Paulo", "SP", "04310000"));
            battalion10 = battalionRepository.save(battalion10);

            System.out.println("✓ Batalhões inseridos: 10 registros (com endereços)");

            // 4. Inserir Endereços para os Usuários
            Address addressUser1 = new Address(
                    "Rua das Flores",
                    123,
                    "Apto 45",
                    "Jardim Paulista",
                    "São Paulo",
                    "SP",
                    "01419000"
            );

            Address addressUser2 = new Address(
                    "Avenida Paulista",
                    1000,
                    "Bloco B",
                    "Bela Vista",
                    "São Paulo",
                    "SP",
                    "01310100"
            );

            Address addressUser3 = new Address(
                    "Rua Augusta",
                    750,
                    null,
                    "Consolação",
                    "São Paulo",
                    "SP",
                    "01305000"
            );

            Address addressUser4 = new Address(
                    "Rua Oscar Freire",
                    200,
                    "Casa 3",
                    "Pinheiros",
                    "São Paulo",
                    "SP",
                    "05409010"
            );

            Address addressUser5 = new Address(
                    "Rua da Consolação",
                    1500,
                    "Apto 102",
                    "Centro",
                    "São Paulo",
                    "SP",
                    "01301000"
            );

            Address addressUserAdmin = new Address(
                    "Avenida dos Bombeiros",
                    1,
                    "Comando Geral",
                    "Centro Administrativo",
                    "São Paulo",
                    "SP",
                    "01000000"
            );

            // 5. Inserir Usuários (Users)
            // Usuário 1 - Coronel
            User user1 = new User(
                    "joao.silva",
                    "joao.silva@bombeiros.pe.gov.br",
                    "11999887766",
                    "12345678901",
                    "BM-001234",
                    "João Silva Santos",
                    OffsetDateTime.parse("1975-03-15T00:00:00-03:00"),
                    "M"
            );
            user1.setPassword(passwordEncoder.encode("senha123"));
            user1.setPatent(coronel);
            user1.setBattalion(battalion1);
            user1.setAddress(addressUser1);
            user1 = userRepository.save(user1);

            // Usuário 2 - Capitão
            User user2 = new User(
                    "maria.oliveira",
                    "maria.oliveira@bombeiros.pe.gov.br",
                    "11999887767",
                    "12345678902",
                    "BM-001235",
                    "Maria Oliveira Costa",
                    OffsetDateTime.parse("1980-07-22T00:00:00-03:00"),
                    "F"
            );
            user2.setPassword(passwordEncoder.encode("senha123"));
            user2.setPatent(capitao);
            user2.setBattalion(battalion1);
            user2.setAddress(addressUser2);
            user2 = userRepository.save(user2);

            // Usuário 3 - Tenente
            User user3 = new User(
                    "carlos.santos",
                    "carlos.santos@bombeiros.pe.gov.br",
                    "11999887768",
                    "12345678903",
                    "BM-001236",
                    "Carlos Santos Lima",
                    OffsetDateTime.parse("1985-11-10T00:00:00-03:00"),
                    "M"
            );
            user3.setPassword(passwordEncoder.encode("senha123"));
            user3.setPatent(tenente);
            user3.setBattalion(battalion2);
            user3.setAddress(addressUser3);
            user3 = userRepository.save(user3);

            // Usuário 4 - Sargento
            User user4 = new User(
                    "ana.ferreira",
                    "ana.ferreira@bombeiros.pe.gov.br",
                    "11999887769",
                    "12345678904",
                    "BM-001237",
                    "Ana Ferreira Souza",
                    OffsetDateTime.parse("1990-05-18T00:00:00-03:00"),
                    "F"
            );
            user4.setPassword(passwordEncoder.encode("senha123"));
            user4.setPatent(sargento);
            user4.setBattalion(battalion2);
            user4.setAddress(addressUser4);
            user4 = userRepository.save(user4);

            // Usuário 5 - Soldado
            User user5 = new User(
                    "pedro.almeida",
                    "pedro.almeida@bombeiros.pe.gov.br",
                    "11999887770",
                    "12345678905",
                    "BM-001238",
                    "Pedro Almeida Rocha",
                    OffsetDateTime.parse("1995-09-25T00:00:00-03:00"),
                    "M"
            );
            user5.setPassword(passwordEncoder.encode("senha123"));
            user5.setPatent(soldado);
            user5.setBattalion(battalion3);
            user5.setAddress(addressUser5);
            user5 = userRepository.save(user5);

            // Usuário Admin - Coronel
            User userAdmin = new User(
                    "admin",
                    "admin@bombeiros.pe.gov.br",
                    "11999999999",
                    "00000000000",
                    "BM-000000",
                    "Administrador do Sistema",
                    OffsetDateTime.parse("1970-01-01T00:00:00-03:00"),
                    "M"
            );
            userAdmin.setPassword(passwordEncoder.encode("root102030"));
            userAdmin.setPatent(coronel);
            userAdmin.setBattalion(battalion1);
            userAdmin.setAddress(addressUserAdmin);
            userAdmin = userRepository.save(userAdmin);

            // Usuários adicionais (7 ao 10)
            User user7 = new User(
                    "fernanda.costa",
                    "fernanda.costa@bombeiros.pe.gov.br",
                    "11999887771",
                    "12345678906",
                    "BM-001239",
                    "Fernanda Costa Alves",
                    OffsetDateTime.parse("1988-12-03T00:00:00-03:00"),
                    "F"
            );
            user7.setPassword(passwordEncoder.encode("senha123"));
            user7.setPatent(cabo);
            user7.setBattalion(battalion4);
            user7.setAddress(new Address("Rua Haddock Lobo", 350, "Apto 201", "Cerqueira César", "São Paulo", "SP", "01414001"));
            user7 = userRepository.save(user7);

            User user8 = new User(
                    "roberto.lima",
                    "roberto.lima@bombeiros.pe.gov.br",
                    "11999887772",
                    "12345678907",
                    "BM-001240",
                    "Roberto Lima Ferreira",
                    OffsetDateTime.parse("1992-04-15T00:00:00-03:00"),
                    "M"
            );
            user8.setPassword(passwordEncoder.encode("senha123"));
            user8.setPatent(soldado);
            user8.setBattalion(battalion5);
            user8.setAddress(new Address("Avenida Rebouças", 2100, null, "Pinheiros", "São Paulo", "SP", "05402400"));
            user8 = userRepository.save(user8);

            User user9 = new User(
                    "juliana.santos",
                    "juliana.santos@bombeiros.pe.gov.br",
                    "11999887773",
                    "12345678908",
                    "BM-001241",
                    "Juliana Santos Martins",
                    OffsetDateTime.parse("1987-08-20T00:00:00-03:00"),
                    "F"
            );
            user9.setPassword(passwordEncoder.encode("senha123"));
            user9.setPatent(tenente2);
            user9.setBattalion(battalion6);
            user9.setAddress(new Address("Rua Alameda Santos", 1500, "Conjunto 305", "Paraíso", "São Paulo", "SP", "01419002"));
            user9 = userRepository.save(user9);

            User user10 = new User(
                    "marcelo.alves",
                    "marcelo.alves@bombeiros.pe.gov.br",
                    "11999887774",
                    "12345678909",
                    "BM-001242",
                    "Marcelo Alves Pereira",
                    OffsetDateTime.parse("1991-06-30T00:00:00-03:00"),
                    "M"
            );
            user10.setPassword(passwordEncoder.encode("senha123"));
            user10.setPatent(subtenente);
            user10.setBattalion(battalion7);
            user10.setAddress(new Address("Rua Pamplona", 900, "Casa", "Jardim Paulista", "São Paulo", "SP", "01405001"));
            user10 = userRepository.save(user10);

            System.out.println("✓ Usuários inseridos: 10 registros (com endereços)");

            // 6. Inserir Tipos de Ocorrência
            OccurrenceType incendio = new OccurrenceType("Incêndio");
            incendio = occurrenceTypeRepository.save(incendio);

            OccurrenceType resgate = new OccurrenceType("Resgate");
            resgate = occurrenceTypeRepository.save(resgate);

            OccurrenceType emergenciaMedica = new OccurrenceType("Emergência Médica");
            emergenciaMedica = occurrenceTypeRepository.save(emergenciaMedica);

            OccurrenceType vazamento = new OccurrenceType("Vazamento");
            vazamento = occurrenceTypeRepository.save(vazamento);

            OccurrenceType acidente = new OccurrenceType("Acidente de Trânsito");
            acidente = occurrenceTypeRepository.save(acidente);

            OccurrenceType desabamento = new OccurrenceType("Desabamento");
            desabamento = occurrenceTypeRepository.save(desabamento);

            OccurrenceType inundacao = new OccurrenceType("Inundação");
            inundacao = occurrenceTypeRepository.save(inundacao);

            OccurrenceType quedaArvore = new OccurrenceType("Queda de Árvore");
            quedaArvore = occurrenceTypeRepository.save(quedaArvore);

            OccurrenceType animalPerigoso = new OccurrenceType("Animal Perigoso");
            animalPerigoso = occurrenceTypeRepository.save(animalPerigoso);

            OccurrenceType outros = new OccurrenceType("Outros");
            outros = occurrenceTypeRepository.save(outros);

            System.out.println("✓ Tipos de Ocorrência inseridos: 10 registros");

            // 7. Inserir Subtipos de Ocorrência
            OccurrenceSubType incendioResidencial = new OccurrenceSubType("Incêndio Residencial", incendio);
            incendioResidencial = occurrenceSubTypeRepository.save(incendioResidencial);

            OccurrenceSubType incendioComercial = new OccurrenceSubType("Incêndio Comercial", incendio);
            incendioComercial = occurrenceSubTypeRepository.save(incendioComercial);

            OccurrenceSubType incendioVeicular = new OccurrenceSubType("Incêndio Veicular", incendio);
            incendioVeicular = occurrenceSubTypeRepository.save(incendioVeicular);

            OccurrenceSubType resgateAltura = new OccurrenceSubType("Resgate em Altura", resgate);
            resgateAltura = occurrenceSubTypeRepository.save(resgateAltura);

            OccurrenceSubType resgateEspaco = new OccurrenceSubType("Resgate em Espaço Confinado", resgate);
            resgateEspaco = occurrenceSubTypeRepository.save(resgateEspaco);

            OccurrenceSubType paradaCardiaca = new OccurrenceSubType("Parada Cardíaca", emergenciaMedica);
            paradaCardiaca = occurrenceSubTypeRepository.save(paradaCardiaca);

            OccurrenceSubType vazamentoGas = new OccurrenceSubType("Vazamento de Gás", vazamento);
            vazamentoGas = occurrenceSubTypeRepository.save(vazamentoGas);

            OccurrenceSubType acidenteCarro = new OccurrenceSubType("Acidente com Automóvel", acidente);
            acidenteCarro = occurrenceSubTypeRepository.save(acidenteCarro);

            OccurrenceSubType desabamentoImovel = new OccurrenceSubType("Desabamento de Imóvel", desabamento);
            desabamentoImovel = occurrenceSubTypeRepository.save(desabamentoImovel);

            OccurrenceSubType alagamento = new OccurrenceSubType("Alagamento de Via", inundacao);
            alagamento = occurrenceSubTypeRepository.save(alagamento);

            System.out.println("✓ Subtipos de Ocorrência inseridos: 10 registros");

            // 8. Inserir Veículos
            Vehicle veiculo1 = new Vehicle();
            veiculo1.setName("ABT-01");
            veiculo1.setBattalion(battalion1);
            veiculo1 = vehicleRepository.save(veiculo1);

            Vehicle veiculo2 = new Vehicle();
            veiculo2.setName("ABT-02");
            veiculo2.setBattalion(battalion1);
            veiculo2 = vehicleRepository.save(veiculo2);

            Vehicle veiculo3 = new Vehicle();
            veiculo3.setName("ABT-03");
            veiculo3.setBattalion(battalion2);
            veiculo3 = vehicleRepository.save(veiculo3);

            Vehicle veiculo4 = new Vehicle();
            veiculo4.setName("ASE-01");
            veiculo4.setBattalion(battalion2);
            veiculo4 = vehicleRepository.save(veiculo4);

            Vehicle veiculo5 = new Vehicle();
            veiculo5.setName("ASE-02");
            veiculo5.setBattalion(battalion3);
            veiculo5 = vehicleRepository.save(veiculo5);

            Vehicle veiculo6 = new Vehicle();
            veiculo6.setName("ABT-04");
            veiculo6.setBattalion(battalion3);
            veiculo6 = vehicleRepository.save(veiculo6);

            Vehicle veiculo7 = new Vehicle();
            veiculo7.setName("ABT-05");
            veiculo7.setBattalion(battalion4);
            veiculo7 = vehicleRepository.save(veiculo7);

            Vehicle veiculo8 = new Vehicle();
            veiculo8.setName("ASE-03");
            veiculo8.setBattalion(battalion5);
            veiculo8 = vehicleRepository.save(veiculo8);

            Vehicle veiculo9 = new Vehicle();
            veiculo9.setName("ABT-06");
            veiculo9.setBattalion(battalion6);
            veiculo9 = vehicleRepository.save(veiculo9);

            Vehicle veiculo10 = new Vehicle();
            veiculo10.setName("ASE-04");
            veiculo10.setBattalion(battalion7);
            veiculo10 = vehicleRepository.save(veiculo10);

            System.out.println("✓ Veículos inseridos: 10 registros");

            // 9. Inserir Ocorrências
            Occurrence occurrence1 = new Occurrence();
            occurrence1.setOccurrenceHasVictims(true);
            occurrence1.setOccurrenceRequester("Maria da Silva");
            occurrence1.setOccurrenceRequesterPhoneNumber("11988887777");
            occurrence1.setOccurrenceSubType(incendioResidencial);
            occurrence1.setAddress(new Address("Rua das Palmeiras", 456, "Casa 2", "Vila Madalena", "São Paulo", "SP", "05434010"));
            occurrence1.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence1.setOccurrenceDetails("Incêndio em residência de 2 andares, controlado pela equipe");
            occurrence1.setLatitude(new BigDecimal("-23.5505199"));
            occurrence1.setLongitude(new BigDecimal("-46.6333094"));
            occurrence1.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(5));
            occurrence1 = occurrenceRepository.save(occurrence1);

            Occurrence occurrence2 = new Occurrence();
            occurrence2.setOccurrenceHasVictims(false);
            occurrence2.setOccurrenceRequester("José Santos");
            occurrence2.setOccurrenceRequesterPhoneNumber("11977776666");
            occurrence2.setOccurrenceSubType(incendioComercial);
            occurrence2.setAddress(new Address("Avenida Paulista", 2000, "Loja 15", "Bela Vista", "São Paulo", "SP", "01310300"));
            occurrence2.setStatus(OccurrenceStatus.EM_ATENDIMENTO);
            occurrence2.setOccurrenceDetails("Princípio de incêndio em estabelecimento comercial");
            occurrence2.setLatitude(new BigDecimal("-23.5613920"));
            occurrence2.setLongitude(new BigDecimal("-46.6561840"));
            occurrence2 = occurrenceRepository.save(occurrence2);

            Occurrence occurrence3 = new Occurrence();
            occurrence3.setOccurrenceHasVictims(true);
            occurrence3.setOccurrenceRequester("Ana Paula Oliveira");
            occurrence3.setOccurrenceRequesterPhoneNumber("11966665555");
            occurrence3.setOccurrenceSubType(acidenteCarro);
            occurrence3.setAddress(new Address("Marginal Pinheiros", 5000, null, "Brooklin", "São Paulo", "SP", "04571020"));
            occurrence3.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence3.setOccurrenceDetails("Colisão entre 2 veículos, 3 vítimas atendidas");
            occurrence3.setLatitude(new BigDecimal("-23.6168710"));
            occurrence3.setLongitude(new BigDecimal("-46.6983780"));
            occurrence3.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(3));
            occurrence3 = occurrenceRepository.save(occurrence3);

            Occurrence occurrence4 = new Occurrence();
            occurrence4.setOccurrenceHasVictims(false);
            occurrence4.setOccurrenceRequester("Pedro Henrique");
            occurrence4.setOccurrenceRequesterPhoneNumber("11955554444");
            occurrence4.setOccurrenceSubType(vazamentoGas);
            occurrence4.setAddress(new Address("Rua Augusta", 1000, "Edifício Central", "Consolação", "São Paulo", "SP", "01305100"));
            occurrence4.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence4.setOccurrenceDetails("Vazamento de gás em edifício, área isolada");
            occurrence4.setLatitude(new BigDecimal("-23.5489430"));
            occurrence4.setLongitude(new BigDecimal("-46.6617890"));
            occurrence4.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(7));
            occurrence4 = occurrenceRepository.save(occurrence4);

            Occurrence occurrence5 = new Occurrence();
            occurrence5.setOccurrenceHasVictims(true);
            occurrence5.setOccurrenceRequester("Carla Souza");
            occurrence5.setOccurrenceRequesterPhoneNumber("11944443333");
            occurrence5.setOccurrenceSubType(paradaCardiaca);
            occurrence5.setAddress(new Address("Rua Oscar Freire", 500, null, "Jardim Paulista", "São Paulo", "SP", "01426001"));
            occurrence5.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence5.setOccurrenceDetails("Atendimento de emergência médica, vítima estabilizada");
            occurrence5.setLatitude(new BigDecimal("-23.5623210"));
            occurrence5.setLongitude(new BigDecimal("-46.6691540"));
            occurrence5.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(1));
            occurrence5 = occurrenceRepository.save(occurrence5);

            Occurrence occurrence6 = new Occurrence();
            occurrence6.setOccurrenceHasVictims(false);
            occurrence6.setOccurrenceRequester("Ricardo Almeida");
            occurrence6.setOccurrenceRequesterPhoneNumber("11933332222");
            occurrence6.setOccurrenceSubType(resgateAltura);
            occurrence6.setAddress(new Address("Avenida Berrini", 1500, "Torre Norte", "Brooklin", "São Paulo", "SP", "04571000"));
            occurrence6.setStatus(OccurrenceStatus.EM_ATENDIMENTO);
            occurrence6.setOccurrenceDetails("Resgate de trabalhador em andaime");
            occurrence6.setLatitude(new BigDecimal("-23.6234560"));
            occurrence6.setLongitude(new BigDecimal("-46.6892340"));
            occurrence6 = occurrenceRepository.save(occurrence6);

            Occurrence occurrence7 = new Occurrence();
            occurrence7.setOccurrenceHasVictims(false);
            occurrence7.setOccurrenceRequester("Fernanda Lima");
            occurrence7.setOccurrenceRequesterPhoneNumber("11922221111");
            occurrence7.setOccurrenceSubType(alagamento);
            occurrence7.setAddress(new Address("Avenida Tiradentes", 800, null, "Luz", "São Paulo", "SP", "01101000"));
            occurrence7.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence7.setOccurrenceDetails("Alagamento controlado, via liberada");
            occurrence7.setLatitude(new BigDecimal("-23.5344560"));
            occurrence7.setLongitude(new BigDecimal("-46.6367890"));
            occurrence7.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(10));
            occurrence7 = occurrenceRepository.save(occurrence7);

            Occurrence occurrence8 = new Occurrence();
            occurrence8.setOccurrenceHasVictims(true);
            occurrence8.setOccurrenceRequester("Bruno Fernandes");
            occurrence8.setOccurrenceRequesterPhoneNumber("11911110000");
            occurrence8.setOccurrenceSubType(desabamentoImovel);
            occurrence8.setAddress(new Address("Rua do Glicério", 300, null, "Liberdade", "São Paulo", "SP", "01514000"));
            occurrence8.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence8.setOccurrenceDetails("Desabamento parcial, 2 vítimas resgatadas");
            occurrence8.setLatitude(new BigDecimal("-23.5567890"));
            occurrence8.setLongitude(new BigDecimal("-46.6278900"));
            occurrence8.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(15));
            occurrence8 = occurrenceRepository.save(occurrence8);

            Occurrence occurrence9 = new Occurrence();
            occurrence9.setOccurrenceHasVictims(false);
            occurrence9.setOccurrenceRequester("Juliana Martins");
            occurrence9.setOccurrenceRequesterPhoneNumber("11999998888");
            occurrence9.setOccurrenceSubType(incendioVeicular);
            occurrence9.setAddress(new Address("Rodovia dos Bandeirantes", 25000, "KM 25", "Perus", "São Paulo", "SP", "05119000"));
            occurrence9.setStatus(OccurrenceStatus.EM_ATENDIMENTO);
            occurrence9.setOccurrenceDetails("Incêndio em veículo na rodovia");
            occurrence9.setLatitude(new BigDecimal("-23.3987650"));
            occurrence9.setLongitude(new BigDecimal("-46.7456780"));
            occurrence9 = occurrenceRepository.save(occurrence9);

            Occurrence occurrence10 = new Occurrence();
            occurrence10.setOccurrenceHasVictims(false);
            occurrence10.setOccurrenceRequester("Marcos Silva");
            occurrence10.setOccurrenceRequesterPhoneNumber("11988887766");
            occurrence10.setOccurrenceSubType(resgateEspaco);
            occurrence10.setAddress(new Address("Rua Vergueiro", 2500, "Subsolo", "Vila Mariana", "São Paulo", "SP", "04102000"));
            occurrence10.setStatus(OccurrenceStatus.CONCLUIDA);
            occurrence10.setOccurrenceDetails("Resgate em espaço confinado, operação concluída");
            occurrence10.setLatitude(new BigDecimal("-23.5798760"));
            occurrence10.setLongitude(new BigDecimal("-46.6389450"));
            occurrence10.setOccurrenceArrivalTime(LocalDateTime.now().minusDays(2));
            occurrence10 = occurrenceRepository.save(occurrence10);

            System.out.println("✓ Ocorrências inseridas: 10 registros");

            // 10. Inserir Relações Ocorrência-Usuários
            OccurenceUsers occUser1 = new OccurenceUsers(occurrence1, user1);
            occurrenceUsersRepository.save(occUser1);

            OccurenceUsers occUser2 = new OccurenceUsers(occurrence1, user2);
            occurrenceUsersRepository.save(occUser2);

            OccurenceUsers occUser3 = new OccurenceUsers(occurrence2, user3);
            occurrenceUsersRepository.save(occUser3);

            OccurenceUsers occUser4 = new OccurenceUsers(occurrence3, user4);
            occurrenceUsersRepository.save(occUser4);

            OccurenceUsers occUser5 = new OccurenceUsers(occurrence3, user5);
            occurrenceUsersRepository.save(occUser5);

            OccurenceUsers occUser6 = new OccurenceUsers(occurrence4, user7);
            occurrenceUsersRepository.save(occUser6);

            OccurenceUsers occUser7 = new OccurenceUsers(occurrence5, user8);
            occurrenceUsersRepository.save(occUser7);

            OccurenceUsers occUser8 = new OccurenceUsers(occurrence7, user9);
            occurrenceUsersRepository.save(occUser8);

            OccurenceUsers occUser9 = new OccurenceUsers(occurrence8, user10);
            occurrenceUsersRepository.save(occUser9);

            OccurenceUsers occUser10 = new OccurenceUsers(occurrence10, user1);
            occurrenceUsersRepository.save(occUser10);

            System.out.println("✓ Relações Ocorrência-Usuários inseridas: 10 registros");

            System.out.println("\n===========================================");
            System.out.println("Dados iniciais inseridos com sucesso!");
            System.out.println("===========================================");
            System.out.println("\nCredenciais de acesso:");
            System.out.println("★  Username: admin           | Patente: Coronel     | Senha: root102030 | Batalhão: 1º [ADMIN]");
            System.out.println("1. Username: joao.silva      | Patente: Coronel     | Senha: senha123   | Batalhão: 1º");
            System.out.println("2. Username: maria.oliveira  | Patente: Capitão     | Senha: senha123   | Batalhão: 1º");
            System.out.println("3. Username: carlos.santos   | Patente: Tenente     | Senha: senha123   | Batalhão: 2º");
            System.out.println("4. Username: ana.ferreira    | Patente: Sargento    | Senha: senha123   | Batalhão: 2º");
            System.out.println("5. Username: pedro.almeida   | Patente: Soldado     | Senha: senha123   | Batalhão: 3º");
            System.out.println("6. Username: fernanda.costa  | Patente: Cabo        | Senha: senha123   | Batalhão: 4º");
            System.out.println("7. Username: roberto.lima    | Patente: Soldado     | Senha: senha123   | Batalhão: 5º");
            System.out.println("8. Username: juliana.santos  | Patente: 2º Tenente  | Senha: senha123   | Batalhão: 6º");
            System.out.println("9. Username: marcelo.alves   | Patente: Subtenente  | Senha: senha123   | Batalhão: 7º");
            System.out.println("\n📊 Resumo de dados inseridos:");
            System.out.println("   • 10 Patentes");
            System.out.println("   • 10 Batalhões (com endereços)");
            System.out.println("   • 10 Usuários + 1 Admin (com endereços)");
            System.out.println("   • 10 Tipos de Ocorrência");
            System.out.println("   • 10 Subtipos de Ocorrência");
            System.out.println("   • 10 Veículos");
            System.out.println("   • 10 Ocorrências (com endereços)");
            System.out.println("   • 10 Relações Ocorrência-Usuários");
            System.out.println("===========================================\n");
        };
    }
}
