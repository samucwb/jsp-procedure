# jsp-procedure

Esta aplicação foi construída usando o NetBeans IDE 12.0.
Foi testada usando um servidor tomcat instalado localmente.
Ela utiliza o Banco H2 e o liquibase é inicializado assim que aplicação é carregada.

Os arquivos do liquibase foram criados em modo xml, devendo funcionar em qualquer tipo de base 
suportada. (necessário alterar os fontes).


## Em caso de erros

Caso você não consiga visualizar as páginas já com o conteudo pré-carregado ou encontre algum outro erro
pode ser que você tenha algum problema de configuração com o seu firewall, que esteja impedindo a criação 
do banco de dados H2 em modo server na porta 9093.


## Para executar
Você pode importar este projeto na sua IDE favorita e executar o comandos do maven 
para construir o arquivo .war, optando também por debugá-lo. 

Você também pode subir a aplicação em seu servidor de aplicação importando o 
arquivo war que está dentro da pasta package. 

https://www.liquidweb.com/kb/installing-tomcat-9-on-windows/
