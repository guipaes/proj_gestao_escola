# proj_gestao_escola
ExemploFinalWeb2

Descrição do projeto: o projeto tem por objetivo permitir o cadastro de cursos, disciplinas e alunos. Deve-se considerar qeu os cursos possuem disciplinas e uma disciplina só pertence a um curso. Os alunos fazem apenas um curso e podem se matricular em "n" discplinas desse curso apenas (ou seja, um aluno não pode ser associado a uma disciplina que não seja do seu curso). As displinas podem possuir "n" alunos inscritos.


Criar o projeto (ou importar, se quiser testar o código)

Incuir as dependências:
    - (incluído automaticamente) apontar para o JDK na sua máquina
    - (incluído automaticamente) bibliotecas java-web (normalmente usamos as do container)
    - Bibliotecas ORM (libs do Hibernate): baixe em www.hibernate.org
        --Use todos os jar obrigadorios (required)
        --Use a lib do JPA (na pasta JPA)
    
    - Biblioteca do banco (adotei o MySQL): https://www.mysql.com/products/connector/
        -- drive JDBC do MySQL (adicione o jar)

    - Assegure-se de que as dependências estão configuradas corretamente.

Observe os pacotes organizados para inclusão de classes segundo o padrão MVC (veja pacotes).

    - no modelo, as classes java que modelam meu negócio (os objetos que existem).
    - os controles fazem todo processamento entre a camada de visualização e o modelo.
    - a camada de visualização entre os pacotes foi criada para que sejam incluídas todos os processamentos sobre os dados para que eles sejam preparados para apresentação. A visualização dos usuários são páginas JSP.


[Siga as instruções e você vai aprender]

Veja se tem erros no código. Se tiver, as dependências estão erradas. Corrija.
Crie um banco com o nome que você quiser (eu chamei de "cursos"). Ele não precisa ter nada.
Nesta primeira etapa vá a pasta META-INF (mesmo nível dos pacotes do projeto), abra o arquivo persistence.xml e:
    - observe o atributo "name" do elemento persistence-unit
        --agora abra a classe DAO em br.ufrrj.web2.model.dao e procure pelo método "getManager()". Veja o parâmetro passado para factory
        --volte para o persistence.xml
    - observe o elemento property que aponta para o banco de dados. Cuidado para incluir na URL o nome do banco que você criou.
    - observe também as properties referentes a usuário e senha.


Classes do Modelo
    - São três classes: Aluno, Disciplina e Curso. Todas com mapeamento bidirecional.
        -- atividade: crie o diagrama de classe incluindo nas classes todos os atributos e modelos e as cardinalidades dos relacionamentos entre as classes.
        -- Depois observe as classes e veja que fiz o mapeamento com anotações JPA (usei o básico e deixei ele gerar as demais coisas pelo padrão). O mapeamento são as anotações.
            --- @Entity
            --- @Id (use @GeneratedValue para ele criar um autoincremento no banco sem indicar uma estratégia específica, padrão)
            --- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany
                ----> Veja o atributo mappedBy.
                ----> Veja no código como funcona o CascadeType e os comentários.
                ----> Veja os comentários no código para ver sobre recuperação Lazy e Eager.
            --- @Temporal(TemporalType.TIMESTAMP) para atributos de data para orientar como proceder junto ao BD
            

Testando o modelo: vá no pacote br.ufrrj.web2.applicationTest
    - Abra a classe veja o código. Leia todos os comentários.
    - Rode esse código como um código java comum. Ainda não estamos testando a parte web, apenas o modelo.
    - Volte e veja novamente o mapeamento do modelo (veja item Classes do Modelo deste arquivo)
    - Leia novamente o código da classe de teste e releia os comentários. Essa é uma boa prática para testar primeiro seu modelo e assegurar que ele está funcionando. Assim você pode isolar a parte dos controladores e visualização para encontrar erros na aplicação. Depurar vai ajudar depois, mas saber que o modelo está funcionando é muito bom.
    
    
    
[Parte web]
    Convenções: decidi que as disciplinas seriam cadastradas após a inclusão do curso. Ou seja, cadastro o curso e logo depois as disciplinas. É suficiente para gerar os exemplos.
    Obs: Quando cheguei nesta etapa, já sabia que o modelo e a persistência possuiam o que eu queria. Assim posso focar na parte Web.

    PERGUNTA -> O arquivo persistence.xml da pasta META-INF que está no mesmo nível dos pacotes do projeto precisa ser movido para a pasta WebContent/META-INF do projeto web? Lembre-se que quando o projeto é implementado (ação de deploy no container) ele tem uma estrutura de classes diferente da que vemos na IDE quando estamos desenvolvendo (e diferente da estrutura do java básico).
    
    -> Cuidado com as dependências quando estiver trabalhando com projetos Web. Uma boa prática é colocar todas as dependências na pasta WebContent/WEB-INF/lib e corrigir o Build Path. Assim quando você fizer o deploy ou exportar a aplicação as bibliotecas vão junto. Outra forma é colocar as bibliotecas na pasta lib do container. Se você importou os jars externo, tem que consertar agora.
    
    Quem for PRO-ATIVO pode se dar bem -> Quem leu primeiro e relacionou com o desafio pode ser capaz de respondê-lo... se não entendeu nada é porque você não está acompanhando as mensagens e a matéria. ¬¬ Você tem apenas uma chance.

    - Observem que faltam tratamentos para usuários não bonzinhos. Falta também ter meios de diferenciar curso, disciplina e aluno de outra forma que não seja pelo nome. Do jeito que está, seria necessário garantir que o campo nome dessas classes (e tabela no BD) fossem únicos. Como fazer isso?

    - No trabalho, façam os tratamentos para o sistema não quebrar com uso inadequado.
    
    - Assegure-se de que as dependências estão configuradas corretamente.
    
    - Eu sempre prefiro navegar (inspecionar) adotando uma abordagem topdown. Começo olhando as páginas jsp e depois vejo que serviços elas chamam. Cuidado para não inspecionar no navegador. Você não verá o jsp/jstl.
    
    Exercícios:
    
        1) Rode o projeto web. 
        2) Agora você deve navegar e ver tudo funcionando como usuário. 
        3) Depois abra o código e os utilize como exemplo. 
        4) Será necessário parar e analisar o código, seguir o fluxo do que foi programado.
