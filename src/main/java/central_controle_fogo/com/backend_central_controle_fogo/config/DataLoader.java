package central_controle_fogo.com.backend_central_controle_fogo.config;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.Roles;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.UserRoles;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.*;
import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import central_controle_fogo.com.backend_central_controle_fogo.model.vehicles.Vehicle;
import central_controle_fogo.com.backend_central_controle_fogo.repository.address.IAddressRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRolesRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IUserRolesRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceNatureRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceStatusRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceSubTypeRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceTypeRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceUsersRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceVehiclesRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrenceBattalionsRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport.OccurrencePhotosRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatentRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.vehicle.IVehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            IPatentRepository patentRepository,
            IBattalionRepository battalionRepository,
            IAddressRepository addressRepository,
            IRepositoryUser userRepository,
            IRolesRepository rolesRepository,
            IUserRolesRepository userRolesRepository,
            IVehicleRepository vehicleRepository,
            OccurrenceNatureRepository occurrenceNatureRepository,
            OccurrenceStatusRepository occurrenceStatusRepository,
            OccurrenceTypeRepository occurrenceTypeRepository,
            OccurrenceSubTypeRepository occurrenceSubTypeRepository,
            OccurrenceRepository occurrenceRepository,
            OccurrenceUsersRepository occurrenceUsersRepository,
            OccurrenceVehiclesRepository occurrenceVehiclesRepository,
            OccurrenceBattalionsRepository occurrenceBattalionsRepository,
            OccurrencePhotosRepository occurrencePhotosRepository,
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
            user1.setPassword(passwordEncoder.encode("Senha@123"));
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
            user2.setPassword(passwordEncoder.encode("Senha@123"));
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
            user3.setPassword(passwordEncoder.encode("Senha@123"));
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
            user4.setPassword(passwordEncoder.encode("Senha@123"));
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
            user5.setPassword(passwordEncoder.encode("Senha@123"));
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
            userAdmin.setPassword(passwordEncoder.encode("Admin@123"));
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
            user7.setPassword(passwordEncoder.encode("Senha@123"));
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
            user8.setPassword(passwordEncoder.encode("Senha@123"));
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
            user9.setPassword(passwordEncoder.encode("Senha@123"));
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
            user10.setPassword(passwordEncoder.encode("Senha@123"));
            user10.setPatent(subtenente);
            user10.setBattalion(battalion7);
            user10.setAddress(new Address("Rua Pamplona", 900, "Casa", "Jardim Paulista", "São Paulo", "SP", "01405001"));
            user10 = userRepository.save(user10);

            System.out.println("✓ Usuários inseridos: 10 registros (com endereços)");

            // 6. Inserir Permissões (Roles)
            Roles roleAdministrador = new Roles();
            roleAdministrador.setName("ADMINISTRADOR");
            roleAdministrador.setDescription("Acesso total ao sistema, incluindo gerenciamento de usuários e configurações");
            roleAdministrador = rolesRepository.save(roleAdministrador);

            Roles roleAgente = new Roles();
            roleAgente.setName("AGENTE");
            roleAgente.setDescription("Acesso operacional para registro e atualização de ocorrências");
            roleAgente = rolesRepository.save(roleAgente);

            Roles roleObservador = new Roles();
            roleObservador.setName("OBSERVADOR");
            roleObservador.setDescription("Acesso apenas para visualização de dados");
            roleObservador = rolesRepository.save(roleObservador);

            System.out.println("✓ Permissões inseridas: 3 registros");

            // 7. Associar Permissões aos Usuários
            userRolesRepository.save(new UserRoles(userAdmin, roleAdministrador));
            userRolesRepository.save(new UserRoles(user1, roleAgente));
            userRolesRepository.save(new UserRoles(user2, roleAgente));
            userRolesRepository.save(new UserRoles(user3, roleAgente));
            userRolesRepository.save(new UserRoles(user4, roleAgente));
            userRolesRepository.save(new UserRoles(user5, roleAgente));
            userRolesRepository.save(new UserRoles(user7, roleObservador));
            userRolesRepository.save(new UserRoles(user8, roleAgente));
            userRolesRepository.save(new UserRoles(user9, roleAgente));
            userRolesRepository.save(new UserRoles(user10, roleAgente));

            System.out.println("✓ Permissões associadas aos usuários");

            // 8. Inserir Status de Ocorrência
            central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus statusAguardando = new central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus();
            statusAguardando.setName("AGUARDANDO ATENDIMENTO");
            statusAguardando = occurrenceStatusRepository.save(statusAguardando);

            central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus statusEmAtendimento = new central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus();
            statusEmAtendimento.setName("EM ATENDIMENTO");
            statusEmAtendimento = occurrenceStatusRepository.save(statusEmAtendimento);

            central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus statusConcluida = new central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus();
            statusConcluida.setName("CONCLUIDA");
            statusConcluida = occurrenceStatusRepository.save(statusConcluida);

            central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus statusFalsoAlarme = new central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus();
            statusFalsoAlarme.setName("FALSO ALARME");
            statusFalsoAlarme = occurrenceStatusRepository.save(statusFalsoAlarme);

            central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus statusCancelada = new central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus();
            statusCancelada.setName("CANCELADA");
            statusCancelada = occurrenceStatusRepository.save(statusCancelada);

            System.out.println("✓ Status de Ocorrência inseridos: 5 registros");

            // 9. Inserir Classificação de Ocorrências CBMPE
            System.out.println("\n=== Iniciando inserção da Classificação CBMPE ===");
            int countNature = 0, countType = 0, countSubType = 0;

            // ========================================
            // NATUREZA 1: ATENDIMENTO PRÉ-HOSPITALAR
            // ========================================
            OccurrenceNature nat1 = new OccurrenceNature();
            nat1.setName("ATENDIMENTO PRÉ-HOSPITALAR");
            nat1 = occurrenceNatureRepository.save(nat1);
            countNature++;

            // Grupo 1: Acidente de Trânsito - Atropelamento
            OccurrenceType t1g1 = new OccurrenceType();
            t1g1.setName("Acidente de Trânsito - Atropelamento");
            t1g1.setNature(nat1);
            t1g1 = occurrenceTypeRepository.save(t1g1);
            countType++;
            String[] sub1g1 = {"Auto passeio","Barco, Balsa ou Congêneres","Bicicleta","Caminhão","Carroça","Jet-ski","Lancha","Máquina Agrícola","Metrô","Moto","Navio","Ônibus ou Micro-ônibus","Rebocador","Táxi","Trem","Van ou similar","Veículo de carga não perigosa","Veículo de carga perigosa","Veículo de emergência, policial ou similar","Outro"};
            for(String s:sub1g1){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g1));countSubType++;}

            // Grupo 2: Acidente de Trânsito - Capotamento
            OccurrenceType t1g2 = new OccurrenceType();
            t1g2.setName("Acidente de Trânsito - Capotamento");
            t1g2.setNature(nat1);
            t1g2 = occurrenceTypeRepository.save(t1g2);
            countType++;
            String[] sub1g2 = {"Auto passeio","Caminhão","Máquina Agrícola","Moto","Metrô","Ônibus ou Micro-ônibus","Táxi","Trem","Van ou similar","Veículo de carga não perigosa","Veículo de carga perigosa","Veículo de emergência, policial ou similar","Outro"};
            for(String s:sub1g2){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g2));countSubType++;}

            // Grupo 3: Acidente de Trânsito - Colisão Abalroamento
            OccurrenceType t1g3 = new OccurrenceType();
            t1g3.setName("Acidente de Trânsito - Colisão Abalroamento");
            t1g3.setNature(nat1);
            t1g3 = occurrenceTypeRepository.save(t1g3);
            countType++;
            String[] sub1g3 = {"Engavetamento Diverso","Auto passeio x Auto passeio","Auto passeio x Bicicleta","Auto passeio x Caminhão","Auto passeio x Máquinas Agrícola","Auto passeio x Metrô","Auto passeio x Motocicleta","Auto passeio x Ônibus ou Micro-ônibus","Auto passeio x Táxi","Auto passeio x Trem","Auto passeio x Van ou similar","Auto passeio x Veículo de carga não perigosa","Auto passeio x Veículo de carga perigosa","Auto passeio x Veículo de emerg. policial ou similar","Barco, Balsa ou congêneres x Barco, Balsa ou congêneres","Barco, Balsa ou congêneres x Jet-ski","Jet-ski x Jet-ski","Máquina Agrícola x Máquina Agrícola","Máquina Agrícola x Motocicleta","Máquina Agrícola x Ônibus ou Micro-ônibus","Máquina Agrícola x Táxi","Máquina Agrícola x Van ou similar","Máquina Agrícola x Veículo de carga não perigosa","Máquina Agrícola x Veículo de carga perigosa","Máquina Agrícola x Veículo de emerg., policial ou similar","Metro x Metrô","Motocicleta x Motocicleta","Motocicleta x Ônibus ou Micro-ônibus","Motocicleta x Táxi","Motocicleta x Van ou similar","Motocicleta x Veículo de carga não perigosa","Motocicleta x Veículo de carga perigosa","Motocicleta x Veículo de emerg., policial ou similar","Ônibus ou Micro-ônibus x Ônibus ou Micro-ônibus","Ônibus ou Micro-ônibus x Táxi","Ônibus ou Micro-ônibus x Trem","Ônibus ou Micro-ônibus x Van ou similar","Ônibus ou Micro-ônibus x Veículo de carga não perigosa","Ônibus ou Micro-ônibus x Veículo de carga perigosa","Ônibus ou Micro-ônibus x Veículo de emerg., policial ou similar","Táxi x Táxi","Táxi x Trem","Táxi x Van ou similar","Táxi x Veículo de carga não perigosa","Táxi x Veículo de carga perigosa","Táxi x Veículo de emerg., policial ou similar","Trem x Van ou similar","Trem x Veículo de carga não perigosa","Trem x Veículo de carga perigosa","Trem x Veículo de emerg., policial ou similar","Van ou similar x Van ou similar","Van ou similar x Veículo de carga perigosa","Van ou similar x Veículo de emerg., policial ou similar","Van ou similar x Veículo de carga não perigosa","Veículo de carga não perigosa x Veículo de carga não perigosa","Veículo de carga não perigosa x Veículo de carga perigosa","Veículo de carga não perigosa x Veículo de emerg., policial ou similar","Veículo de carga perigosa x Veículo de carga perigosa","Veículo de carga perigosa x V. de emerg., policial ou similar","V. de emerg. policial ou similar x V. de emerg., policial ou similar","Outro"};
            for(String s:sub1g3){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g3));countSubType++;}

            // Grupo 4: Acidente de Trânsito - Choque
            OccurrenceType t1g4 = new OccurrenceType();
            t1g4.setName("Acidente de Trânsito - Choque");
            t1g4.setNature(nat1);
            t1g4 = occurrenceTypeRepository.save(t1g4);
            countType++;
            String[] sub1g4 = {"Animal","Auto passeio","Barco, balsa ou congêneres","Bicicleta","Caminhão","Jet-ski","Máquina Agrícola","Metro","Motocicleta","Ônibus ou Micro-ônibus","Veículo de carga não perigosa","Trem","Van ou similar","Táxi","Veículo de carga perigosa","Veículo de emergência, policial ou similar","Outro"};
            for(String s:sub1g4){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g4));countSubType++;}

            // Grupo 5: Incidente com Animal - Aquático
            OccurrenceType t1g5 = new OccurrenceType();
            t1g5.setName("Incidente com Animal - Aquático");
            t1g5.setNature(nat1);
            t1g5 = occurrenceTypeRepository.save(t1g5);
            countType++;
            String[] sub1g5 = {"Tubarão","Outro"};
            for(String s:sub1g5){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g5));countSubType++;}

            // Grupo 6: Incidente com Animal - Inseto
            OccurrenceType t1g6 = new OccurrenceType();
            t1g6.setName("Incidente com Animal - Inseto");
            t1g6.setNature(nat1);
            t1g6 = occurrenceTypeRepository.save(t1g6);
            countType++;
            String[] sub1g6 = {"Abelha","Marimbondo","Outros"};
            for(String s:sub1g6){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g6));countSubType++;}

            // Grupo 7: Incidente com Animal - com Peçonha
            OccurrenceType t1g7 = new OccurrenceType();
            t1g7.setName("Incidente com Animal - com Peçonha");
            t1g7.setNature(nat1);
            t1g7 = occurrenceTypeRepository.save(t1g7);
            countType++;
            String[] sub1g7 = {"Cobra","Escorpião","Aranha","Outro"};
            for(String s:sub1g7){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g7));countSubType++;}

            // Grupo 8: Incidente com Animal - Sem Peçonha
            OccurrenceType t1g8 = new OccurrenceType();
            t1g8.setName("Incidente com Animal - Sem Peçonha");
            t1g8.setNature(nat1);
            t1g8 = occurrenceTypeRepository.save(t1g8);
            countType++;
            String[] sub1g8 = {"Cão","Gato","Bovino","Suíno","Caprino","Ovino","Equino","Aves","Cobra Não Peçonhenta","Aranha Não Peçonhenta","Réptil","Outro"};
            for(String s:sub1g8){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g8));countSubType++;}

            // Grupo 9: Emergências Clínicas Diversas
            OccurrenceType t1g9 = new OccurrenceType();
            t1g9.setName("Emergências Clínicas Diversas");
            t1g9.setNature(nat1);
            t1g9 = occurrenceTypeRepository.save(t1g9);
            countType++;
            String[] sub1g9 = {"Coma Alcoólico","Desmaio/Síncope","Envenenamento","Acidente Vascular Cerebral","Hemorragia","Convulsão","Hipoglicemia","Hipotermia","Intoxicação Endógena","Contaminação","Outro"};
            for(String s:sub1g9){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g9));countSubType++;}

            // Grupo 10: Emergência Cardíaca
            OccurrenceType t1g10 = new OccurrenceType();
            t1g10.setName("Emergência Cardíaca");
            t1g10.setNature(nat1);
            t1g10 = occurrenceTypeRepository.save(t1g10);
            countType++;
            String[] sub1g10 = {"Parada Cardiorrespiratória","Problemas Cardíacos","Crise Hipertensiva","Outro"};
            for(String s:sub1g10){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g10));countSubType++;}

            // Grupo 11: Emergência Obstétrica
            OccurrenceType t1g11 = new OccurrenceType();
            t1g11.setName("Emergência Obstétrica");
            t1g11.setNature(nat1);
            t1g11 = occurrenceTypeRepository.save(t1g11);
            countType++;
            String[] sub1g11 = {"Gravidez","Parto","Outro"};
            for(String s:sub1g11){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g11));countSubType++;}

            // Grupo 12: Emergência Psiquiátrica
            OccurrenceType t1g12 = new OccurrenceType();
            t1g12.setName("Emergência Psiquiátrica");
            t1g12.setNature(nat1);
            t1g12 = occurrenceTypeRepository.save(t1g12);
            countType++;
            String[] sub1g12 = {"Distúrbio Mental com risco","Distúrbio Mental sem risco","Crise Traumática","Crise de Abstinência/Psicose induzida","Outro"};
            for(String s:sub1g12){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g12));countSubType++;}

            // Grupo 13: Emergência Respiratória
            OccurrenceType t1g13 = new OccurrenceType();
            t1g13.setName("Emergência Respiratória");
            t1g13.setNature(nat1);
            t1g13 = occurrenceTypeRepository.save(t1g13);
            countType++;
            String[] sub1g13 = {"Engasgo","Asma","Insuficiência Respiratória","Outro"};
            for(String s:sub1g13){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g13));countSubType++;}

            // Grupo 14: Queimadura Elétrica / Choque
            OccurrenceType t1g14 = new OccurrenceType();
            t1g14.setName("Queimadura Elétrica / Choque");
            t1g14.setNature(nat1);
            t1g14 = occurrenceTypeRepository.save(t1g14);
            countType++;
            String[] sub1g14 = {"Fios Energizados de Casa","Fios Energizados de Comércio Indústria etc.","Fios energizados de Postes","Ferramentas Elétricas","Eletrodoméstico","Motores Elétricos","Baterias","Cerca Elétrica","Raios","Outro"};
            for(String s:sub1g14){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g14));countSubType++;}

            // Grupo 15: Queimadura Química
            OccurrenceType t1g15 = new OccurrenceType();
            t1g15.setName("Queimadura Química");
            t1g15.setNature(nat1);
            t1g15 = occurrenceTypeRepository.save(t1g15);
            countType++;
            String[] sub1g15 = {"Ácido","Base","Orgânico","Inorgânico","Outro"};
            for(String s:sub1g15){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g15));countSubType++;}

            // Grupo 16: Queimadura Térmica
            OccurrenceType t1g16 = new OccurrenceType();
            t1g16.setName("Queimadura Térmica");
            t1g16.setNature(nat1);
            t1g16 = occurrenceTypeRepository.save(t1g16);
            countType++;
            String[] sub1g16 = {"Fogo","Fogos de Artifício","Eletrodoméstico","Líquidos Quentes","Vapor de Água","Superficie Quente","Gelo / Criogênica","Outro"};
            for(String s:sub1g16){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g16));countSubType++;}

            // Grupo 17: Queimadura Biológica
            OccurrenceType t1g17 = new OccurrenceType();
            t1g17.setName("Queimadura Biológica");
            t1g17.setNature(nat1);
            t1g17 = occurrenceTypeRepository.save(t1g17);
            countType++;
            String[] sub1g17 = {"Caravela","Lagarta","Planta Urtiga","Outro"};
            for(String s:sub1g17){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g17));countSubType++;}

            // Grupo 18: Queimadura Radioativa
            OccurrenceType t1g18 = new OccurrenceType();
            t1g18.setName("Queimadura Radioativa");
            t1g18.setNature(nat1);
            t1g18 = occurrenceTypeRepository.save(t1g18);
            countType++;
            String[] sub1g18 = {"Insolação","Material Radioativo","Outro"};
            for(String s:sub1g18){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g18));countSubType++;}

            // Grupo 19: Queda
            OccurrenceType t1g19 = new OccurrenceType();
            t1g19.setName("Queda");
            t1g19.setNature(nat1);
            t1g19 = occurrenceTypeRepository.save(t1g19);
            countType++;
            String[] sub1g19 = {"Queda de Moto","Queda de Bicicleta","Queda de Nível Acima de 2m","Queda de Nível Abaixo de 2m","Queda da Própria Altura","Queda de Animal","Outro"};
            for(String s:sub1g19){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g19));countSubType++;}

            // Grupo 20: Trauma por Objeto Perfuro Cortante
            OccurrenceType t1g20 = new OccurrenceType();
            t1g20.setName("Trauma por Objeto Perfuro Cortante");
            t1g20.setNature(nat1);
            t1g20 = occurrenceTypeRepository.save(t1g20);
            countType++;
            String[] sub1g20 = {"Faca","Facão","Metal Qualquer","Tesoura","Alicate","Madeira","Espeto","Punhal","Espada","Serra","Serrote","Chave de Fenda","Enxada","Enxadeco","Chibanca","Picareta","Lâmina","Máquina de Moer","Máquina de Prensar","Não identificado","Outro"};
            for(String s:sub1g20){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g20));countSubType++;}

            // Grupo 21: Trauma por Objeto Contundente
            OccurrenceType t1g21 = new OccurrenceType();
            t1g21.setName("Trauma por Objeto Contundente");
            t1g21.setNature(nat1);
            t1g21 = occurrenceTypeRepository.save(t1g21);
            countType++;
            String[] sub1g21 = {"Pedra","Martelo","Marreta","Madeira","Metal Qualquer","Porta de Carro","Porta, portão, grade ou similar","Não Identificado","Outro"};
            for(String s:sub1g21){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g21));countSubType++;}

            // Grupo 22: Vítima de Agressão
            OccurrenceType t1g22 = new OccurrenceType();
            t1g22.setName("Vítima de Agressão");
            t1g22.setNature(nat1);
            t1g22 = occurrenceTypeRepository.save(t1g22);
            countType++;
            String[] sub1g22 = {"Arma Branca","Arma de Fogo","Fisica","Sexual","Objeto Contundente","Outro"};
            for(String s:sub1g22){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g22));countSubType++;}

            // Grupo 23: Acidentes Diversos
            OccurrenceType t1g23 = new OccurrenceType();
            t1g23.setName("Acidentes Diversos");
            t1g23.setNature(nat1);
            t1g23 = occurrenceTypeRepository.save(t1g23);
            countType++;
            String[] sub1g23 = {"Acidente com linha de cerol","Atendimento Secundário","Vítima de desastre","Outro"};
            for(String s:sub1g23){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t1g23));countSubType++;}

            // Grupo 24: APH Diversos
            OccurrenceType t1g24 = new OccurrenceType();
            t1g24.setName("APH Diversos");
            t1g24.setNature(nat1);
            t1g24 = occurrenceTypeRepository.save(t1g24);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Diversos",t1g24));countSubType++;

            System.out.println("✓ Natureza 1 (ATENDIMENTO PRÉ-HOSPITALAR): 24 grupos inseridos");

            // ========================================
            // NATUREZA 2: INCÊNDIO
            // ========================================
            OccurrenceNature nat2 = new OccurrenceNature();
            nat2.setName("INCÊNDIO");
            nat2 = occurrenceNatureRepository.save(nat2);
            countNature++;

            // Grupo 1: Incêndio em Edificação - Concentração de Público
            OccurrenceType t2g1 = new OccurrenceType();
            t2g1.setName("Incêndio em Edificação - Concentração de Público");
            t2g1.setNature(nat2);
            t2g1 = occurrenceTypeRepository.save(t2g1);
            countType++;
            String[] sub2g1 = {"Academia de ginástica ou de dança ou similar","Auditório ou similar","Bar, lanchonete ou similar","Biblioteca","Boate, casa noturna ou similar","Centro de convenções ou de exposições, feira ou similar","Cinema","Circo, parque de diversões ou similar","Clube, salão de festas ou similar","Estádio de futebol","Galeria de exposições ou similar","Ginásio de esportes","Igreja, templo ou similar","Museu","Playground","Restaurante","Shopping ou similar","Teatro","Terminal de passageiros Aeroporto/Rodoviária ou similar","Outro"};
            for(String s:sub2g1){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g1));countSubType++;}

            // Grupo 2: Incêndio em Edificação - Comercial
            OccurrenceType t2g2 = new OccurrenceType();
            t2g2.setName("Incêndio em Edificação - Comercial");
            t2g2.setNature(nat2);
            t2g2 = occurrenceTypeRepository.save(t2g2);
            countType++;
            String[] sub2g2 = {"Açougue, frigorífico, matadouro ou similar","Agência bancária","Agência de câmbio ou similar","Agência de locação","Agência de veículo","Agência de viagem, turismo ou similar","Alfaiataria","Barbearia, salão de beleza ou similar","Cartório","Casa lotérica","Copiadora, reprografia ou similar","Empresa de segurança ou similar","Empresa de transporte de passageiro ou de carga","Empresa importadora ou exportadora","Escritório","Farmácia, perfumaria ou similar","Funerária","Livraria, papelaria ou similar","Loja de departamentos","Galeria","Oficina","Mercado","Padaria ou similar","Posto de combustível","Posto de revenda de gás liquefeito de petróleo (GLP)","Prédio Público","Supermercado","Laboratório","Outro"};
            for(String s:sub2g2){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g2));countSubType++;}

            // Grupo 3: Incêndio em Edificação - Especial
            OccurrenceType t2g3 = new OccurrenceType();
            t2g3.setName("Incêndio em Edificação - Especial");
            t2g3.setNature(nat2);
            t2g3 = occurrenceTypeRepository.save(t2g3);
            countType++;
            String[] sub2g3 = {"Armazém, galpão ou similar","Arquivo público ou privado","Canteiro de obras","Central de processamento de dados (CPD)","Composição ferroviária","Empresa de comunicação","Estação de tratamento ou distribuição de água","Estação ou subestação de distribuição de energia elétrica","Estacionamento, garagem ou similar","Fábrica ou revenda de fogos de artifício ou artefato explosivo","Laboratório","Parque de tancagem ou tanque isolado","Quartel da polícia, bombeiro, forças armadas ou afim","Terreno baldio, lote vago ou similar","Outro"};
            for(String s:sub2g3){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g3));countSubType++;}

            // Grupo 4: Incêndio em Edificação - Hospitalar
            OccurrenceType t2g4 = new OccurrenceType();
            t2g4.setName("Incêndio em Edificação - Hospitalar");
            t2g4.setNature(nat2);
            t2g4 = occurrenceTypeRepository.save(t2g4);
            countType++;
            String[] sub2g4 = {"Asilo, casa geriátrica ou similar","Clínica veterinária ou similar","Hospital","Policlínica, clínica ou similar","Outro"};
            for(String s:sub2g4){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g4));countSubType++;}

            // Grupo 5: Incêndio em Edificação - Residencial
            OccurrenceType t2g5 = new OccurrenceType();
            t2g5.setName("Incêndio em Edificação - Residencial");
            t2g5.setNature(nat2);
            t2g5 = occurrenceTypeRepository.save(t2g5);
            countType++;
            String[] sub2g5 = {"Unifamiliar Casa residência","Multifamiliar Edificação elevada","Multifamiliar Casas conjugadas","Aglomerado Subnormal Favela","Coletiva Penitenciária ou afim","Coletivo Pensionato","Coletivo Convento","Coletivo Orfanato","Outro"};
            for(String s:sub2g5){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g5));countSubType++;}

            // Grupo 6: Incêndio em Edificação - Escolar
            OccurrenceType t2g6 = new OccurrenceType();
            t2g6.setName("Incêndio em Edificação - Escolar");
            t2g6.setNature(nat2);
            t2g6 = occurrenceTypeRepository.save(t2g6);
            countType++;
            String[] sub2g6 = {"Creche","Ensino fundamental ou médio","Ensino superior","Ensino profissionalizante","Outro"};
            for(String s:sub2g6){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g6));countSubType++;}

            // Grupo 7: Incêndio em Edificação - Transitória
            OccurrenceType t2g7 = new OccurrenceType();
            t2g7.setName("Incêndio em Edificação - Transitória");
            t2g7.setNature(nat2);
            t2g7 = occurrenceTypeRepository.save(t2g7);
            countType++;
            String[] sub2g7 = {"Hotel ou apart hotel","Motel","Pousada","Albergue","Outro"};
            for(String s:sub2g7){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g7));countSubType++;}

            // Grupo 8: Incêndio em Edificação - Depósito
            OccurrenceType t2g8 = new OccurrenceType();
            t2g8.setName("Incêndio em Edificação - Depósito");
            t2g8.setNature(nat2);
            t2g8 = occurrenceTypeRepository.save(t2g8);
            countType++;
            String[] sub2g8 = {"Algodão, tecido, estopa ou similar","Borracha, pneu ou similar","Eletrodoméstico ou similar","Explosivo, munição ou similar","Líquido inflamável","Madeira, móveis ou similar","Papel, livros ou similar","Produtos farmacêuticos","Plástico ou similar","Outro"};
            for(String s:sub2g8){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g8));countSubType++;}

            // Grupo 9: Incêndio em Edificação - Industrial
            OccurrenceType t2g9 = new OccurrenceType();
            t2g9.setName("Incêndio em Edificação - Industrial");
            t2g9.setNature(nat2);
            t2g9 = occurrenceTypeRepository.save(t2g9);
            countType++;
            String[] sub2g9 = {"Destilaria, refinaria ou similar","Eletroeletrônica","Madeireira","Metalúrgica","Naval","Química","Siderúrgica","Têxtil","Alimentícia","Farmacêutica","Outro"};
            for(String s:sub2g9){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g9));countSubType++;}

            // Grupo 10: Incêndio em Edificação - Outros
            OccurrenceType t2g10 = new OccurrenceType();
            t2g10.setName("Incêndio em Edificação - Outros");
            t2g10.setNature(nat2);
            t2g10 = occurrenceTypeRepository.save(t2g10);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Outro",t2g10));countSubType++;

            // Grupo 11: Incêndio em Meio de Transporte Aéreo
            OccurrenceType t2g11 = new OccurrenceType();
            t2g11.setName("Incêndio em Meio de Transporte Aéreo");
            t2g11.setNature(nat2);
            t2g11 = occurrenceTypeRepository.save(t2g11);
            countType++;
            String[] sub2g11 = {"Aeronave de asa fixa","Aeronave de asa rotativa","Outro"};
            for(String s:sub2g11){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g11));countSubType++;}

            // Grupo 12: Incêndio em Meio de Transporte Aquático
            OccurrenceType t2g12 = new OccurrenceType();
            t2g12.setName("Incêndio em Meio de Transporte Aquático");
            t2g12.setNature(nat2);
            t2g12 = occurrenceTypeRepository.save(t2g12);
            countType++;
            String[] sub2g12 = {"Barco, balsa ou congêneres","Jet-ski","Lancha","Navio","Rebocador","Outro"};
            for(String s:sub2g12){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g12));countSubType++;}

            // Grupo 13: Incêndio em Meio de Transporte Terrestre
            OccurrenceType t2g13 = new OccurrenceType();
            t2g13.setName("Incêndio em Meio de Transporte Terrestre");
            t2g13.setNature(nat2);
            t2g13 = occurrenceTypeRepository.save(t2g13);
            countType++;
            String[] sub2g13 = {"Auto passeio","Bicicleta","Caminhão","Carroça","Máquina Agrícola","Motocicleta","Ônibus ou Micro-ônibus","Táxi","Van ou similar","Veículo de carga não perigosa","Veículo de carga perigosa","Viatura de emergência, policial ou similar","Metrô","Trem","Outro"};
            for(String s:sub2g13){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g13));countSubType++;}

            // Grupo 14: Incêndio em Vegetação
            OccurrenceType t2g14 = new OccurrenceType();
            t2g14.setName("Incêndio em Vegetação");
            t2g14.setNature(nat2);
            t2g14 = occurrenceTypeRepository.save(t2g14);
            countType++;
            String[] sub2g14 = {"Arborização Pública","Caatinga","Capoeira","Cerrado","Cultura Agrícola, Monocultura ou Similar","Floresta Plantada / Reflorestamento","Jardim","Mangue","Mata ou Floresta Nativa","Mato","Pasto","Outro"};
            for(String s:sub2g14){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g14));countSubType++;}

            // Grupo 15: Incêndio em Área de Descarte
            OccurrenceType t2g15 = new OccurrenceType();
            t2g15.setName("Incêndio em Área de Descarte");
            t2g15.setNature(nat2);
            t2g15 = occurrenceTypeRepository.save(t2g15);
            countType++;
            String[] sub2g15 = {"Aterro Sanitário","Lixão","Local especial para tratamento e reciclagem","Monturo","Outro"};
            for(String s:sub2g15){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g15));countSubType++;}

            // Grupo 16: Incêndio em Via Pública
            OccurrenceType t2g16 = new OccurrenceType();
            t2g16.setName("Incêndio em Via Pública");
            t2g16.setNature(nat2);
            t2g16 = occurrenceTypeRepository.save(t2g16);
            countType++;
            String[] sub2g16 = {"Protesto","Fiação Elétrica de Poste","Outro"};
            for(String s:sub2g16){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t2g16));countSubType++;}

            // Grupo 17: Incêndios Diversos
            OccurrenceType t2g17 = new OccurrenceType();
            t2g17.setName("Incêndios Diversos");
            t2g17.setNature(nat2);
            t2g17 = occurrenceTypeRepository.save(t2g17);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Diversos",t2g17));countSubType++;

            System.out.println("✓ Natureza 2 (INCÊNDIO): 17 grupos inseridos");

            // ========================================
            // NATUREZA 3: SALVAMENTO
            // ========================================
            OccurrenceNature nat3 = new OccurrenceNature();
            nat3.setName("SALVAMENTO");
            nat3 = occurrenceNatureRepository.save(nat3);
            countNature++;

            // Grupo 1: Evento com Pessoa
            OccurrenceType t3g1 = new OccurrenceType();
            t3g1.setName("Evento com Pessoa");
            t3g1.setNature(nat3);
            t3g1 = occurrenceTypeRepository.save(t3g1);
            countType++;
            String[] sub3g1 = {"Afogamento","Alagamento","Desabamento / Desmoronamento","Deslizamento / Escorregamento","Enchente / Inundação","Enxurrada","Explosão","Pessoa em local de difícil acesso (Trilha/Montanha/Caverna)","Pessoa Ilhada","Pessoa Perdida / Desaparecida","Preso em Espaço Confinado","Preso em Elevador","Preso em Ferragem de veículo","Preso em Máquina ou Equipamento","Preso em Altura","Queda","Remoção de Objeto","Retirada de anel ou similar","Resgate aquático","Salvamento em incêndio","Soterramento","Tentativa de Suicídio","Transporte de Vítima","Outro"};
            for(String s:sub3g1){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t3g1));countSubType++;}

            // Grupo 2: Evento com Animal
            OccurrenceType t3g2 = new OccurrenceType();
            t3g2.setName("Evento com Animal");
            t3g2.setNature(nat3);
            t3g2 = occurrenceTypeRepository.save(t3g2);
            countType++;
            String[] sub3g2 = {"Aquático","Aves","Bovino","Canino Cão","Canino outro","Caprino","Equino","Felino Gato","Felino outro","Ovino","Suíno","Inseto Abelha","Inseto Maribondo","Inseto outro","Silvestre Bicho Preguiça","Silvestre Cobra","Silvestre Jacaré","Silvestre Tatu","Silvestre Tamanduá","Silvestre outro","Outro"};
            for(String s:sub3g2){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t3g2));countSubType++;}

            // Grupo 3: Evento com Árvore
            OccurrenceType t3g3 = new OccurrenceType();
            t3g3.setName("Evento com Árvore");
            t3g3.setNature(nat3);
            t3g3 = occurrenceTypeRepository.save(t3g3);
            countType++;
            String[] sub3g3 = {"Queda de Árvore em via pública","Queda de Árvore sobre imóveis","Queda de Árvore sobre veículos","Outro"};
            for(String s:sub3g3){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t3g3));countSubType++;}

            // Grupo 4: Evento com Meio de Transporte
            OccurrenceType t3g4 = new OccurrenceType();
            t3g4.setName("Evento com Meio de Transporte");
            t3g4.setNature(nat3);
            t3g4 = occurrenceTypeRepository.save(t3g4);
            countType++;
            String[] sub3g4 = {"Engavetamento","Descarrilamento","Tombamento","Derrapagem de Aeronave","Pouso Forçado","Queda de Aeronave","Queda de Aeronave em meio líquido","Embarcação à Deriva","Embarcação Naufrágio","Outro"};
            for(String s:sub3g4){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t3g4));countSubType++;}

            // Grupo 5: Evento com Objeto
            OccurrenceType t3g5 = new OccurrenceType();
            t3g5.setName("Evento com Objeto");
            t3g5.setNature(nat3);
            t3g5 = occurrenceTypeRepository.save(t3g5);
            countType++;
            String[] sub3g5 = {"Abertura Forçada","Explosão","Explosão de Vaso ou Tubo pressurizado","Objeto em Local de Risco","Reflutuação de objetos","Queda ou Desprendimento","Outro"};
            for(String s:sub3g5){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t3g5));countSubType++;}

            // Grupo 6: Evento com Cadáver
            OccurrenceType t3g6 = new OccurrenceType();
            t3g6.setName("Evento com Cadáver");
            t3g6.setNature(nat3);
            t3g6 = occurrenceTypeRepository.save(t3g6);
            countType++;
            String[] sub3g6 = {"Busca","Recuperação","Outro"};
            for(String s:sub3g6){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t3g6));countSubType++;}

            // Grupo 7: Salvamento Diverso
            OccurrenceType t3g7 = new OccurrenceType();
            t3g7.setName("Salvamento Diverso");
            t3g7.setNature(nat3);
            t3g7 = occurrenceTypeRepository.save(t3g7);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Diverso",t3g7));countSubType++;

            System.out.println("✓ Natureza 3 (SALVAMENTO): 7 grupos inseridos");

            // ========================================
            // NATUREZA 4: PRODUTOS PERIGOSOS
            // ========================================
            OccurrenceNature nat4 = new OccurrenceNature();
            nat4.setName("PRODUTOS PERIGOSOS");
            nat4 = occurrenceNatureRepository.save(nat4);
            countNature++;

            // Grupo 1: Explosão
            OccurrenceType t4g1 = new OccurrenceType();
            t4g1.setName("Explosão");
            t4g1.setNature(nat4);
            t4g1 = occurrenceTypeRepository.save(t4g1);
            countType++;
            String[] sub4g1 = {"Substâncias Explosivas","Gases","Líquidos Inflamáveis","Liquidos Não Inflamáveis","Sólidos Inflamáveis","Substâncias Oxidantes e Peróxidos Orgânicos","Substâncias Tóxicas","Substâncias Infectantes","Materiais Radioativos","Substâncias Corrosivas","Substâncias e Artigos Perigosos Diversos","Biológicos","Produto Não Classificado","Não Identificado","Outro"};
            for(String s:sub4g1){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t4g1));countSubType++;}

            // Grupo 2: Vazamento
            OccurrenceType t4g2 = new OccurrenceType();
            t4g2.setName("Vazamento");
            t4g2.setNature(nat4);
            t4g2 = occurrenceTypeRepository.save(t4g2);
            countType++;
            String[] sub4g2 = {"Substâncias Explosivas","Gás Liquefeito de Petróleo","Gás Natural / Gás Natural Veicular","Acetileno","Outros Gases","Líquidos Inflamáveis","Liquidos Não Inflamáveis","Sólidos Inflamáveis","Substâncias Oxidantes e Peróxidos Orgânicos","Substâncias Tóxicas","Substâncias Infectantes","Materiais Radioativos","Substâncias Corrosivas","Substâncias e Artigos Perigosos Diversos","Biológicos","Produto Não Classificado","Não Identificado","Outro"};
            for(String s:sub4g2){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t4g2));countSubType++;}

            // Grupo 3: Derramamento
            OccurrenceType t4g3 = new OccurrenceType();
            t4g3.setName("Derramamento");
            t4g3.setNature(nat4);
            t4g3 = occurrenceTypeRepository.save(t4g3);
            countType++;
            String[] sub4g3 = {"Líquidos Inflamáveis","Líquidos Não Inflamáveis","Sólidos Inflamáveis","Substâncias Oxidantes e Peróxidos Orgânicos","Substâncias Tóxicas","Substâncias Infectantes","Materiais Radioativos","Substâncias Corrosivas","Substâncias e Artigos Perigosos Diversos","Biológicos","Produto Não Classificado","Não Identificado","Outro"};
            for(String s:sub4g3){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t4g3));countSubType++;}

            // Grupo 4: Contaminação
            OccurrenceType t4g4 = new OccurrenceType();
            t4g4.setName("Contaminação");
            t4g4.setNature(nat4);
            t4g4 = occurrenceTypeRepository.save(t4g4);
            countType++;
            String[] sub4g4 = {"Substâncias Explosivas","Líquidos Inflamáveis","Líquidos Não Inflamáveis","Sólidos Inflamáveis","Substâncias Oxidantes e Peróxidos Orgânicos","Substâncias Tóxicas","Substâncias Infectantes","Materiais Radioativos","Substâncias Corrosivas","Substâncias e Artigos Perigosos Diversos","Biológicos","Produto Não Classificado","Não Identificado","Outro"};
            for(String s:sub4g4){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t4g4));countSubType++;}

            // Grupo 5: Incêndio
            OccurrenceType t4g5 = new OccurrenceType();
            t4g5.setName("Incêndio");
            t4g5.setNature(nat4);
            t4g5 = occurrenceTypeRepository.save(t4g5);
            countType++;
            String[] sub4g5 = {"Substâncias Explosivas","Gases","Líquidos Inflamáveis","Líquidos Não Inflamáveis","Sólidos Inflamáveis","Substâncias Oxidantes e Peróxidos Orgânicos","Substâncias Tóxicas","Substâncias Infectantes","Materiais Radioativos","Substâncias Corrosivas","Substâncias e Artigos Perigosos Diversos","Biológicos","Produto Não Classificado","Não Identificado"};
            for(String s:sub4g5){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t4g5));countSubType++;}

            // Grupo 6: Produtos Perigosos Diversos
            OccurrenceType t4g6 = new OccurrenceType();
            t4g6.setName("Produtos Perigosos Diversos");
            t4g6.setNature(nat4);
            t4g6 = occurrenceTypeRepository.save(t4g6);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Diversos",t4g6));countSubType++;

            System.out.println("✓ Natureza 4 (PRODUTOS PERIGOSOS): 6 grupos inseridos");

            // ========================================
            // NATUREZA 5: PREVENÇÃO
            // ========================================
            OccurrenceNature nat5 = new OccurrenceNature();
            nat5.setName("PREVENÇÃO");
            nat5 = occurrenceNatureRepository.save(nat5);
            countNature++;

            // Grupo 1: Apoio em Operações
            OccurrenceType t5g1 = new OccurrenceType();
            t5g1.setName("Apoio em Operações");
            t5g1.setNature(nat5);
            t5g1 = occurrenceTypeRepository.save(t5g1);
            countType++;
            String[] sub5g1 = {"Ameaça de Bomba ou artefato explosivo","Pouso e Decolagem","Prevenção em Instrução","Reintegração de Posse","Rebelião em Estabelecimento Prisional","Erradicação de Plantio de Drogas","Busca e Apreensão","Protesto","Composição de Comboios de veiculos","Ativação de alarme","Abertura de Porta","Lavagem de Pista","Outro"};
            for(String s:sub5g1){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t5g1));countSubType++;}

            // Grupo 2: Evento Festivo
            OccurrenceType t5g2 = new OccurrenceType();
            t5g2.setName("Evento Festivo");
            t5g2.setNature(nat5);
            t5g2 = occurrenceTypeRepository.save(t5g2);
            countType++;
            String[] sub5g2 = {"Carnavalesco","Junino","Reunião de Público","Queima de fogos","Show Pirotécnico","Outro"};
            for(String s:sub5g2){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t5g2));countSubType++;}

            // Grupo 3: Evento Esportivo
            OccurrenceType t5g3 = new OccurrenceType();
            t5g3.setName("Evento Esportivo");
            t5g3.setNature(nat5);
            t5g3 = occurrenceTypeRepository.save(t5g3);
            countType++;
            String[] sub5g3 = {"Estádio de Futebol","Corrida Automobilística","Vaquejada","Passeio Ciclístico","Corrida de Rua","Outro"};
            for(String s:sub5g3){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t5g3));countSubType++;}

            // Grupo 4: Prevenção Aquática
            OccurrenceType t5g4 = new OccurrenceType();
            t5g4.setName("Prevenção Aquática");
            t5g4.setNature(nat5);
            t5g4 = occurrenceTypeRepository.save(t5g4);
            countType++;
            String[] sub5g4 = {"Ativa e Reativa (Orientação ao Banhista)","Ativação de Posto com Embarcação","Ativação de Posto com Viatura","Prevenção em Orla Marítima","Prevenção em Rio","Prevenção em Lago/Açude","Prevenção em Piscina","Buscada/Levada/Cortejo","Regata","Competição Esportiva","Outro"};
            for(String s:sub5g4){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t5g4));countSubType++;}

            // Grupo 5: Prevenção Diversos
            OccurrenceType t5g5 = new OccurrenceType();
            t5g5.setName("Prevenção Diversos");
            t5g5.setNature(nat5);
            t5g5 = occurrenceTypeRepository.save(t5g5);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Diversos",t5g5));countSubType++;

            System.out.println("✓ Natureza 5 (PREVENÇÃO): 5 grupos inseridos");

            // ========================================
            // NATUREZA 6: ATIVIDADE COMUNITÁRIA
            // ========================================
            OccurrenceNature nat6 = new OccurrenceNature();
            nat6.setName("ATIVIDADE COMUNITÁRIA");
            nat6 = occurrenceNatureRepository.save(nat6);
            countNature++;

            // Grupo 1: Apoio Social
            OccurrenceType t6g1 = new OccurrenceType();
            t6g1.setName("Apoio Social");
            t6g1.setNature(nat6);
            t6g1 = occurrenceTypeRepository.save(t6g1);
            countType++;
            String[] sub6g1 = {"Apoio à Instituição","Apoio à Pessoa","Criança Perdida","Transporte de Obeso","Transporte Inter-Hospitalar","Abastecimento de água","Ação cívico-social","Banho de Neblina","Condução de ataúde","Desfile de Autoridade/Personalidade/Celebridade","Desfile Cívico-Militar","Esgotamento","Reparo e colocação de adriça","Transporte de bem ou produto","Visita preventiva","Visita pós-sinistro","Outro"};
            for(String s:sub6g1){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t6g1));countSubType++;}

            // Grupo 2: Interação Educativa
            OccurrenceType t6g2 = new OccurrenceType();
            t6g2.setName("Interação Educativa");
            t6g2.setNature(nat6);
            t6g2 = occurrenceTypeRepository.save(t6g2);
            countType++;
            String[] sub6g2 = {"Demonstração","Exercício Simulado","Palestra","Exposição","Treinamento","Outro"};
            for(String s:sub6g2){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t6g2));countSubType++;}

            // Grupo 3: Interação Religiosa
            OccurrenceType t6g3 = new OccurrenceType();
            t6g3.setName("Interação Religiosa");
            t6g3.setNature(nat6);
            t6g3 = occurrenceTypeRepository.save(t6g3);
            countType++;
            String[] sub6g3 = {"Cortejo Fúnebre","Condução de andor/Imagem","Outro"};
            for(String s:sub6g3){occurrenceSubTypeRepository.save(new OccurrenceSubType(s,t6g3));countSubType++;}

            // Grupo 4: Atividade Comunitária Diversa
            OccurrenceType t6g4 = new OccurrenceType();
            t6g4.setName("Atividade Comunitária Diversa");
            t6g4.setNature(nat6);
            t6g4 = occurrenceTypeRepository.save(t6g4);
            countType++;
            occurrenceSubTypeRepository.save(new OccurrenceSubType("Diversos",t6g4));countSubType++;

            System.out.println("✓ Natureza 6 (ATIVIDADE COMUNITÁRIA): 4 grupos inseridos");

            System.out.println("\n=== Classificação CBMPE inserida com sucesso! ===");
            System.out.println("Total: " + countNature + " Naturezas, " + countType + " Grupos, " + countSubType + " Subgrupos");

            // Criar variáveis de exemplo para occurrences de teste (usando subtipos da classificação CBMPE)
            OccurrenceSubType incendioResidencial = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Unifamiliar Casa residência") && st.getOccurrenceType().getName().contains("Residencial"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Unifamiliar Casa residência' não encontrado"));
            
            OccurrenceSubType incendioComercial = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Escritório") && st.getOccurrenceType().getName().contains("Comercial"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Escritório' não encontrado"));
            
            OccurrenceSubType acidenteCarro = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Auto passeio x Auto passeio"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Auto passeio x Auto passeio' não encontrado"));
            
            OccurrenceSubType vazamentoGas = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Gás Liquefeito de Petróleo") && st.getOccurrenceType().getName().equals("Vazamento"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Gás Liquefeito de Petróleo' não encontrado"));
            
            OccurrenceSubType paradaCardiaca = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Parada Cardiorrespiratória"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Parada Cardiorrespiratória' não encontrado"));
            
            OccurrenceSubType resgateAltura = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Preso em Altura"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Preso em Altura' não encontrado"));
            
            OccurrenceSubType alagamento = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Alagamento") && st.getOccurrenceType().getName().contains("Pessoa"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Alagamento' não encontrado"));
            
            OccurrenceSubType desabamentoImovel = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Desabamento / Desmoronamento"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Desabamento / Desmoronamento' não encontrado"));
            
            OccurrenceSubType incendioVeicular = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Auto passeio") && st.getOccurrenceType().getName().contains("Meio de Transporte Terrestre"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Auto passeio' (veículo) não encontrado"));
            
            OccurrenceSubType resgateEspaco = occurrenceSubTypeRepository.findAll().stream()
                .filter(st -> st.getName().equals("Preso em Espaço Confinado"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subtipo 'Preso em Espaço Confinado' não encontrado"));

            // 10. Inserir Veículos
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

            // 11. Inserir Ocorrências
            Occurrence occurrence1 = new Occurrence();
            occurrence1.setOccurrenceHasVictims(true);
            occurrence1.setOccurrenceRequester("Maria da Silva");
            occurrence1.setOccurrenceRequesterPhoneNumber("11988887777");
            occurrence1.setOccurrenceSubType(incendioResidencial);
            occurrence1.setAddress(new Address("Rua das Palmeiras", 456, "Casa 2", "Vila Madalena", "São Paulo", "SP", "05434010"));
            occurrence1.setStatus(statusConcluida);
            occurrence1.setOccurrenceDetails("Incêndio em residência de 2 andares, controlado pela equipe");
            occurrence1.setLatitude(new BigDecimal("-23.5505199"));
            occurrence1.setLongitude(new BigDecimal("-46.6333094"));
            occurrence1.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(5));
            occurrence1 = occurrenceRepository.save(occurrence1);

            Occurrence occurrence2 = new Occurrence();
            occurrence2.setOccurrenceHasVictims(false);
            occurrence2.setOccurrenceRequester("José Santos");
            occurrence2.setOccurrenceRequesterPhoneNumber("11977776666");
            occurrence2.setOccurrenceSubType(incendioComercial);
            occurrence2.setAddress(new Address("Avenida Paulista", 2000, "Loja 15", "Bela Vista", "São Paulo", "SP", "01310300"));
            occurrence2.setStatus(statusEmAtendimento);
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
            occurrence3.setStatus(statusConcluida);
            occurrence3.setOccurrenceDetails("Colisão entre 2 veículos, 3 vítimas atendidas");
            occurrence3.setLatitude(new BigDecimal("-23.6168710"));
            occurrence3.setLongitude(new BigDecimal("-46.6983780"));
            occurrence3.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(3));
            occurrence3 = occurrenceRepository.save(occurrence3);

            Occurrence occurrence4 = new Occurrence();
            occurrence4.setOccurrenceHasVictims(false);
            occurrence4.setOccurrenceRequester("Pedro Henrique");
            occurrence4.setOccurrenceRequesterPhoneNumber("11955554444");
            occurrence4.setOccurrenceSubType(vazamentoGas);
            occurrence4.setAddress(new Address("Rua Augusta", 1000, "Edifício Central", "Consolação", "São Paulo", "SP", "01305100"));
            occurrence4.setStatus(statusConcluida);
            occurrence4.setOccurrenceDetails("Vazamento de gás em edifício, área isolada");
            occurrence4.setLatitude(new BigDecimal("-23.5489430"));
            occurrence4.setLongitude(new BigDecimal("-46.6617890"));
            occurrence4.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(7));
            occurrence4 = occurrenceRepository.save(occurrence4);

            Occurrence occurrence5 = new Occurrence();
            occurrence5.setOccurrenceHasVictims(true);
            occurrence5.setOccurrenceRequester("Carla Souza");
            occurrence5.setOccurrenceRequesterPhoneNumber("11944443333");
            occurrence5.setOccurrenceSubType(paradaCardiaca);
            occurrence5.setAddress(new Address("Rua Oscar Freire", 500, null, "Jardim Paulista", "São Paulo", "SP", "01426001"));
            occurrence5.setStatus(statusConcluida);
            occurrence5.setOccurrenceDetails("Atendimento de emergência médica, vítima estabilizada");
            occurrence5.setLatitude(new BigDecimal("-23.5623210"));
            occurrence5.setLongitude(new BigDecimal("-46.6691540"));
            occurrence5.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(1));
            occurrence5 = occurrenceRepository.save(occurrence5);

            Occurrence occurrence6 = new Occurrence();
            occurrence6.setOccurrenceHasVictims(false);
            occurrence6.setOccurrenceRequester("Ricardo Almeida");
            occurrence6.setOccurrenceRequesterPhoneNumber("11933332222");
            occurrence6.setOccurrenceSubType(resgateAltura);
            occurrence6.setAddress(new Address("Avenida Berrini", 1500, "Torre Norte", "Brooklin", "São Paulo", "SP", "04571000"));
            occurrence6.setStatus(statusEmAtendimento);
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
            occurrence7.setStatus(statusConcluida);
            occurrence7.setOccurrenceDetails("Alagamento controlado, via liberada");
            occurrence7.setLatitude(new BigDecimal("-23.5344560"));
            occurrence7.setLongitude(new BigDecimal("-46.6367890"));
            occurrence7.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(10));
            occurrence7 = occurrenceRepository.save(occurrence7);

            Occurrence occurrence8 = new Occurrence();
            occurrence8.setOccurrenceHasVictims(true);
            occurrence8.setOccurrenceRequester("Bruno Fernandes");
            occurrence8.setOccurrenceRequesterPhoneNumber("11911110000");
            occurrence8.setOccurrenceSubType(desabamentoImovel);
            occurrence8.setAddress(new Address("Rua do Glicério", 300, null, "Liberdade", "São Paulo", "SP", "01514000"));
            occurrence8.setStatus(statusConcluida);
            occurrence8.setOccurrenceDetails("Desabamento parcial, 2 vítimas resgatadas");
            occurrence8.setLatitude(new BigDecimal("-23.5567890"));
            occurrence8.setLongitude(new BigDecimal("-46.6278900"));
            occurrence8.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(15));
            occurrence8 = occurrenceRepository.save(occurrence8);

            Occurrence occurrence9 = new Occurrence();
            occurrence9.setOccurrenceHasVictims(false);
            occurrence9.setOccurrenceRequester("Juliana Martins");
            occurrence9.setOccurrenceRequesterPhoneNumber("11999998888");
            occurrence9.setOccurrenceSubType(incendioVeicular);
            occurrence9.setAddress(new Address("Rodovia dos Bandeirantes", 25000, "KM 25", "Perus", "São Paulo", "SP", "05119000"));
            occurrence9.setStatus(statusEmAtendimento);
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
            occurrence10.setStatus(statusConcluida);
            occurrence10.setOccurrenceDetails("Resgate em espaço confinado, operação concluída");
            occurrence10.setLatitude(new BigDecimal("-23.5798760"));
            occurrence10.setLongitude(new BigDecimal("-46.6389450"));
            occurrence10.setOccurrenceArrivalTime(OffsetDateTime.now().minusDays(2));
            occurrence10 = occurrenceRepository.save(occurrence10);

            System.out.println("✓ Ocorrências inseridas: 10 registros");

            // 10. Inserir Relações Ocorrência-Usuários
            OccurrenceUsers occUser1 = new OccurrenceUsers(occurrence1, user1);
            occurrenceUsersRepository.save(occUser1);

            OccurrenceUsers occUser2 = new OccurrenceUsers(occurrence1, user2);
            occurrenceUsersRepository.save(occUser2);

            OccurrenceUsers occUser3 = new OccurrenceUsers(occurrence2, user3);
            occurrenceUsersRepository.save(occUser3);

            OccurrenceUsers occUser4 = new OccurrenceUsers(occurrence3, user4);
            occurrenceUsersRepository.save(occUser4);

            OccurrenceUsers occUser5 = new OccurrenceUsers(occurrence3, user5);
            occurrenceUsersRepository.save(occUser5);

            OccurrenceUsers occUser6 = new OccurrenceUsers(occurrence4, user7);
            occurrenceUsersRepository.save(occUser6);

            OccurrenceUsers occUser7 = new OccurrenceUsers(occurrence5, user8);
            occurrenceUsersRepository.save(occUser7);

            OccurrenceUsers occUser8 = new OccurrenceUsers(occurrence7, user9);
            occurrenceUsersRepository.save(occUser8);

            OccurrenceUsers occUser9 = new OccurrenceUsers(occurrence8, user10);
            occurrenceUsersRepository.save(occUser9);

            OccurrenceUsers occUser10 = new OccurrenceUsers(occurrence10, user1);
            occurrenceUsersRepository.save(occUser10);

            System.out.println("✓ Relações Ocorrência-Usuários inseridas: 10 registros");

            // 11. Inserir Relações Ocorrência-Veículos
            OccurrenceVehicles occVehicle1 = new OccurrenceVehicles(occurrence1, veiculo1);
            occurrenceVehiclesRepository.save(occVehicle1);

            OccurrenceVehicles occVehicle2 = new OccurrenceVehicles(occurrence1, veiculo2);
            occurrenceVehiclesRepository.save(occVehicle2);

            OccurrenceVehicles occVehicle3 = new OccurrenceVehicles(occurrence2, veiculo3);
            occurrenceVehiclesRepository.save(occVehicle3);

            OccurrenceVehicles occVehicle4 = new OccurrenceVehicles(occurrence3, veiculo4);
            occurrenceVehiclesRepository.save(occVehicle4);

            OccurrenceVehicles occVehicle5 = new OccurrenceVehicles(occurrence3, veiculo5);
            occurrenceVehiclesRepository.save(occVehicle5);

            OccurrenceVehicles occVehicle6 = new OccurrenceVehicles(occurrence4, veiculo6);
            occurrenceVehiclesRepository.save(occVehicle6);

            OccurrenceVehicles occVehicle7 = new OccurrenceVehicles(occurrence5, veiculo7);
            occurrenceVehiclesRepository.save(occVehicle7);

            OccurrenceVehicles occVehicle8 = new OccurrenceVehicles(occurrence7, veiculo8);
            occurrenceVehiclesRepository.save(occVehicle8);

            OccurrenceVehicles occVehicle9 = new OccurrenceVehicles(occurrence8, veiculo9);
            occurrenceVehiclesRepository.save(occVehicle9);

            OccurrenceVehicles occVehicle10 = new OccurrenceVehicles(occurrence10, veiculo10);
            occurrenceVehiclesRepository.save(occVehicle10);

            System.out.println("✓ Relações Ocorrência-Veículos inseridas: 10 registros");

            // 12. Inserir Relações Ocorrência-Batalhões
            OccurrenceBattalions occBat1 = new OccurrenceBattalions(occurrence1, battalion1);
            occurrenceBattalionsRepository.save(occBat1);

            OccurrenceBattalions occBat2_1 = new OccurrenceBattalions(occurrence2, battalion2);
            occurrenceBattalionsRepository.save(occBat2_1);

            OccurrenceBattalions occBat3_1 = new OccurrenceBattalions(occurrence3, battalion3);
            occurrenceBattalionsRepository.save(occBat3_1);
            OccurrenceBattalions occBat3_2 = new OccurrenceBattalions(occurrence3, battalion2);
            occurrenceBattalionsRepository.save(occBat3_2);

            OccurrenceBattalions occBat4 = new OccurrenceBattalions(occurrence4, battalion4);
            occurrenceBattalionsRepository.save(occBat4);

            OccurrenceBattalions occBat5 = new OccurrenceBattalions(occurrence5, battalion5);
            occurrenceBattalionsRepository.save(occBat5);

            OccurrenceBattalions occBat6 = new OccurrenceBattalions(occurrence6, battalion6);
            occurrenceBattalionsRepository.save(occBat6);

            OccurrenceBattalions occBat7 = new OccurrenceBattalions(occurrence7, battalion7);
            occurrenceBattalionsRepository.save(occBat7);

            OccurrenceBattalions occBat8 = new OccurrenceBattalions(occurrence8, battalion8);
            occurrenceBattalionsRepository.save(occBat8);

            OccurrenceBattalions occBat9 = new OccurrenceBattalions(occurrence9, battalion9);
            occurrenceBattalionsRepository.save(occBat9);

            OccurrenceBattalions occBat10 = new OccurrenceBattalions(occurrence10, battalion10);
            occurrenceBattalionsRepository.save(occBat10);

            System.out.println("✓ Relações Ocorrência-Batalhões inseridas: 11 registros");

            // 13. Inserir Fotos de Ocorrências (exemplos)
            OccurrencePhotos photo1_1 = new OccurrencePhotos(occurrence1, "https://storage.example.com/occurrences/2024/01/incident_001_photo1.jpg");
            occurrencePhotosRepository.save(photo1_1);

            OccurrencePhotos photo1_2 = new OccurrencePhotos(occurrence1, "https://storage.example.com/occurrences/2024/01/incident_001_photo2.jpg");
            occurrencePhotosRepository.save(photo1_2);

            OccurrencePhotos photo3_1 = new OccurrencePhotos(occurrence3, "https://storage.example.com/occurrences/2024/01/incident_003_scene.jpg");
            occurrencePhotosRepository.save(photo3_1);

            OccurrencePhotos photo5_1 = new OccurrencePhotos(occurrence5, "https://storage.example.com/occurrences/2024/01/incident_005_ambulance.jpg");
            occurrencePhotosRepository.save(photo5_1);

            OccurrencePhotos photo8_1 = new OccurrencePhotos(occurrence8, "https://storage.example.com/occurrences/2024/01/incident_008_rescue1.jpg");
            occurrencePhotosRepository.save(photo8_1);

            OccurrencePhotos photo8_2 = new OccurrencePhotos(occurrence8, "https://storage.example.com/occurrences/2024/01/incident_008_rescue2.jpg");
            occurrencePhotosRepository.save(photo8_2);

            OccurrencePhotos photo8_3 = new OccurrencePhotos(occurrence8, "https://storage.example.com/occurrences/2024/01/incident_008_rescue3.jpg");
            occurrencePhotosRepository.save(photo8_3);

            System.out.println("✓ Fotos de Ocorrências inseridas: 7 registros");

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
            System.out.println("   • 5 Status de Ocorrência");
            System.out.println("   • " + countNature + " Naturezas de Ocorrência (CBMPE)");
            System.out.println("   • " + countType + " Tipos de Ocorrência (Grupos CBMPE)");
            System.out.println("   • " + countSubType + " Subtipos de Ocorrência (Subgrupos CBMPE)");
            System.out.println("   • 10 Veículos");
            System.out.println("   • 10 Ocorrências (com endereços)");
            System.out.println("   • 10 Relações Ocorrência-Usuários");
            System.out.println("   • 10 Relações Ocorrência-Veículos");
            System.out.println("   • 11 Relações Ocorrência-Batalhões");
            System.out.println("   • 7 Fotos de Ocorrências");
            System.out.println("\n💡 Nota: Occurrence1 e Occurrence3 possuem múltiplos usuários e veículos associados");
            System.out.println("💡 Nota: Occurrence3 possui múltiplos batalhões (2º e 3º Batalhões)");
            System.out.println("💡 Nota: Occurrence1, Occurrence3, Occurrence5 e Occurrence8 possuem fotos registradas");
            System.out.println("===========================================\n");
        };
    }
}
