# Folha de Pagamentos (Orientada a Objetos)

Projeto compilado com openjdk 11.0.3 e escrito no Visual Studio Code.

## Instruções de execução

Fornecer as informações sempre sem acentos ou caracteres especiais. Valores monetários devem ser fornecidos com um ponto para representar casas decimais, se preciso (e.g. 1500.30).<br>
Informar inicialmente o primeiro dia da semana do ano atual (terca, sabado), bem como a data de início do programa na forma 'dia mes ano'.
### Adendos sobre as funcionalidades
#### Funcionalidade 1 

Os três tipos de contrato devem ser especificados como: salariado; comissionado; horista<br>
A taxa mensal do sindicato deve ser dada em porcentagem.<br>

Exemplo de uso da funcionalidade 1:<br>
salariado<br>
Joao da silva<br>
sao paulo<br>
3
y
10
1500

#### Funcionalidade 3
Exemplo dos horários de entrada e saída:<br>
7 30<br>
18 45

#### Funcionalidade 5

A identificação fornecida deve ser a do sindicato<br>
A taxa de serviço deve ser um valor avulso.

#### Funcionalidade 6

Deve-se responder com "y" aqueles detalhes que deseja-se mudar, e "n" aqueles que não.<br>
Ao alterar o contrato de um funcionário, deve-se necessariamente fornecer o novo atributo associado (salário, salário e % de comissão, salário/hora).<br>

#### Funcionalidade 7

Essa deve ser a última funcionalidade realizada em cada dia, pois irá passar a data do sistema.

#### Funcionalidade 8

Undo pode ser realizado sobre todas as funcionalidades (inclusive o redo), com exceção dela mesma, 9 e a 10.<br>
Redo só poderá ser utilizado se a funcionalidade utilizada anteriormente foi a 8.

#### Funcionalidade 10

Agendas de pagamento devem sempre seguir as formas:<br>
<li>mensal inteiro ou mensal $<br>
<li>semanal inteiro dia-da-semana(e.g. terca, sabado)<br>