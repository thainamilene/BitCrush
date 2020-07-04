# Projeto Bitcrush

# Descrição Resumida do Projeto/Jogo

Bitcrush é um jogo de puzzle baseado em Candy Crush, o jogo consiste em trocar duas peças de lugar no tabuleiro para criar combinações de 3 ou mais peças do mesmo tipo.

# Equipe
* Thaina Milene de Oliveira - 244570

# Vídeos do Projeto

## Vídeo da Prévia
[vídeo da prévia](https://drive.google.com/file/d/1cZREXR3dypyyvbGZF23Ii20eB0uwlEGt/view?usp=sharing)

## Vídeo do Jogo
[vídeo do jogo](https://drive.google.com/file/d/1-Gm4EdbqBJ_Lt2ERqS7QH-4Tb_wh8NE9/view?usp=sharing)

# Slides do Projeto

## Slides da Prévia
[slides da prévia](https://docs.google.com/presentation/d/1AGrgW_72BtfeeAorsiIWryYgMXez1P_BmrZlbxnDQlE/edit?usp=sharing)

## Slides da Apresentação Final
[slides finais](https://docs.google.com/presentation/d/1X-TS3S5HG9MwJB3k2zf_FLTThtU5w_E21qw7oEUxDRQ/edit?usp=sharing)

## Relatório de Evolução

> <Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.>

# Destaques de Código

~~~java
private void assembleBoard() {
        /*monta o tabuleiro inicial*/
        Random random = new Random();
        int x;
        for (int i = 0; i < 81; i++) {
            board[i] = new NormalPiecesComponent();
            x = random.nextInt(lv); //cria um numero aleatorio que gerara uma peca
            board[i].setType(x);    //o lv e definido pelo nivel que a pessoa escolher: nivel 01 - lv = 05; nivel 02 - lv = 07; nivel 03 - lv = 09
            board[i].setIndex(i);
            board[i].setBoard(this, mainPanel);
        }
        verifyFirstBoard(); // verifica se ja ha combinacoes de 3 pecas no tabuleiro e a elimina
        for (int i = 0; i < 81; i++) {
            /*adiciona os botoes no tabuleiro*/
            this.add(board[i].getButton(), i);
        }
        mainPanel.add(this, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }
~~~


~~~java
 public boolean verifyMovement(int target) {

        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        if ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) { // pecas movendo na mesma linha
            if (type == getPieceOnTop(target) && type == getPieceOnTop(target - 9)) {
                moves[0].setV(true);
                moves[0].setMoveType('c');
                moves[0].addVct(target);
                moves[0].addVct(target - 9);
                moves[0].addVct(target - 18);
                if (type == getPieceInBottom(target)) {
                    moves[0].setMoveType('1');
                    moves[0].addVct(target + 9);
                }
            }
            if (type == getPieceInBottom(target) && type == getPieceInBottom(target + 9)) {
                if (moves[0].isV()) {
                    moves[0].setMoveType('b');
                    moves[0].addVct(target + 18);
                } else {
                    moves[0].setV(true);
                    moves[0].setMoveType('c');
                    moves[0].addVct(target);
                    moves[0].addVct(target + 9);
                    moves[0].addVct(target + 18);
                    if (type == getPieceOnTop(target)) {
                        moves[0].setMoveType('1');
                        moves[0].addVct(target - 9);
                    }
                }
            } else if (!moves[0].isV() && type == getPieceOnTop(target) && type == getPieceInBottom(target)) {
                moves[0].setV(true);
                moves[0].setMoveType('c');
                moves[0].addVct(target);
                moves[0].addVct(target + 9);
                moves[0].addVct(target - 9);
            }
            if (moves[0].getMovetype() != 'b') {
                if (type == getPieceInRight(target) && type == getPieceInRight(target + 1)) {
                    if (moves[0].getMovetype() == '1') {
                        moves[0].removeVct();
                    }
                    if (moves[0].isV()) {
                        moves[0].setMoveType('2');
                    } else {
                        moves[0].setV(true);
                        moves[0].setMoveType('l');
                        moves[0].addVct(target);
                    }
                    moves[0].addVct(target + 1);
                    moves[0].addVct(target + 2);
                }
            }
        }

        ...
}
~~~

~~~java
 private void destroyNormalPieces(int s, int k, int t) {
        /*define as pecas a serem mortas como o tipo morta (setType(-1), e verifica se a peca deve trnasformar-se em um bonus*/
        int i = 0;
        while (i<5 && board[s].getMoves()[k].getVct()[i] != -1) {
            board[board[s].getMoves()[k].getVct()[i]].setType(-1);
            i ++;
        }

        if (i == 4) { //movimento que gera bonus 01
            int aux;
            if (k == 0) {
                aux = board[t].getIndex();
                remove(aux);
                board[t] = new Bonus01Component();
                board[t].setIndex(aux);
                board[t].setBoard(this, mainPanel);
                add(board[t].getButton(), aux);
            } else {
                aux = board[s].getIndex();
                remove(aux);
                board[s] = new Bonus01Component();
                board[s].setIndex(aux);
                board[s].setBoard(this, mainPanel);
                add(board[s].getButton(), aux);
            }
            score.sumScore(7);
        } else if (board[s].getMoves()[k].getMovetype() == 'b') { //movimento que gera bonus 03
            if (k == 0) {
                int aux = board[t].getIndex();
                remove(aux);
                board[t] = new Bonus03Component();
                board[t].setIndex(aux);
                board[t].setBoard(this, mainPanel);
                add(board[t].getButton(), aux);
            } else {
                int aux = board[s].getIndex();
                remove(aux);
                board[s] = new Bonus03Component();
                board[s].setIndex(aux);
                board[s].setBoard(this, mainPanel);
                add(board[s].getButton(), aux);
            }
            score.sumScore(12);
        } else if (i == 5) { //movimento que gera bonus 02
            if (k == 0) {
                int aux = board[t].getIndex();
                remove(aux);
                board[t] = new Bonus02Component();
                board[t].setIndex(aux);
                board[t].setBoard(this, mainPanel);
                add(board[t].getButton(), aux);
            } else {
                int aux = board[s].getIndex();
                remove(aux);
                board[s] = new Bonus02Component();
                board[s].setIndex(aux);
                board[s].setBoard(this, mainPanel);
                add(board[s].getButton(), aux);
            }
            score.sumScore(10);
        } else {
            score.sumScore(5);
        }
    }
~~~

# Destaques de Pattern

## Diagrama do Pattern
 ![Diagrama-composite](diagrama-composite.png)

## Código do Pattern
~~~java

public interface IImages {
	void addImage(ImageIcon image);
}
~~~

~~~java

public class Window extends JFrame implements IImages, ActionListener  {
    private ButtonStyle01 back, jump, next;
    private JPanel buttons, buttonPanel;
    private JLabel imageLabel, title, logo;
    private ButtonStyle01 play, rules;
    private ButtonStyle02 lvl1, lvl2, lvl3;
    private Vector<Tips> tips;
    private int tipsLength = 0;
    private ImageIcon image;
   
    ...
   
    public void addImage(ImageIcon image) {
    	imageLabel.setIcon(image);
    	this.image = image;
    } 
    
    public Component getButton01(int index) {
    	return buttonPanel.getComponent(index);
    }
    
    public void removeButton01 (int index) {
	   buttonPanel.remove(index);
    }
    
    public Tips getTip (int index) {
        return tips.get(index);
    }
    
    public void addTips(Tips tip) {
    	tips.add(tip);
    	tipsLength ++;
    }
    
    public void removeTips (int index) {
    	tips.remove(index);
    }
    
    public Component getTipsButtons (int index) {
    	return buttons.getComponent(index);
    }
    
    public void removeTipsButton (int index) {
 	   buttons.remove(index);
    }
    
    public ButtonStyle02 getLvlButton (int lv) {
    	if (lv == 1) {
    		return lvl1;
    	} 
    	if (lv == 2) {
    		return lvl2;
    	}
    	if (lv == 3) {
    		return lvl3;
    	}
		return null;
    }

    public void setTitle (Icon icon) {
    	title.setIcon(icon);
    }
    
    public void setTitle (String text) {
    	title.setText(text);
    }
   
    public void setLogo (Icon icon) {
    	logo.setIcon(icon);
    }
   
    public void setLogo (String text) {
    	logo.setText(text);
    }
    
    ...
}
~~~

~~~java

public class ScoreboardComponent extends JPanel implements IScoreboard {
	private Vector <JLabel> scoreboard;
	private int scoreboardlength = 0;
   	private JLabel win, lose;

  	...

	public void addImage(ImageIcon image) {
		scoreboard.get(scoreboardlength).setIcon(image);
		scoreboardlength ++;
	}
	
	public void addWinImage(ImageIcon image) {
		win.setIcon(image);
	}
	public void addLoseImage(ImageIcon image) {
		lose.setIcon(image);
	}
}
~~~
~~~ java

public class BoardComponent extends JPanel implements IBoard {
    private final IPieces[] board;

	...

    public IPieces[] getBoard() {
        return board;
    }

	public void addImage(ImageIcon image) {
    	add(new JLabel(image));
    }

    public IPieces getPiece(int index) {
		return index < 81 ? board[index]: null;
    }
    
    ...
}
~~~

~~~java
public class NormalPiecesComponent extends Pieces {

	static ImageIcon[] pieces;

   ...

	public void addImage(ImageIcon image) {
		int i = 0;
		while (pieces[i] != null) {
			i++;
		}
		if (i < 9) {
			pieces[i] = image;
		}
	}
}
~~~

~~~java
public class Bonus01Component extends Pieces {

	private static ImageIcon image = new ImageIcon();
	
   ...

	public void addImage(ImageIcon image) {
		Bonus01Component.image = image;
		button.setIcon(image);
	}
}
~~~

~~~java
public class Bonus02Component extends Pieces {

	private static ImageIcon image = new ImageIcon();
	
   ...

	public void addImage(ImageIcon image) {
		Bonus01Component.image = image;
		button.setIcon(image);
	}
}
~~~

~~~java
public class Bonus03Component extends Pieces {

	private static ImageIcon image = new ImageIcon();
	
   ...

	public void addImage(ImageIcon image) {
		Bonus01Component.image = image;
		button.setIcon(image);
	}
}
~~~

> Primeiramente, organizei o projeto de modo que houvesse uma hierarquia de componentes, assim, a janela principal é composta, na parte principal do jogo, por um placar em cima, o tabuleiro no centro, e o logo embaixo, o placar pode ser composto por diversas imagens, o tabuleiro é composto por até 81 peças, de quatro tipos diferentes. Já nas demais etapas do jogo, a janela é compostas por imagens diversas e botões, Para implementar isso, escolhi colocar uma interface mínima para definir as imagens dos itens, obtando por segurança, ao invés de transparência. A vantagem disso, é o maior controle dos componentes da janela, podendo ser removidos ou adicionados novos, como por exemplo, você consegue colocar quantas dicas quiser, ou não colocar nenhuma e eliminar os botões associados a isso.

# Documentação dos Componentes

# Diagramas

## Diagrama Geral do Projeto

![diagrama geral de organização](diagrama.png)

> O usuário clica em duas peças no tabuleiro, que enviam os dados para serem traduzidos, e devolve ao tabuleiro que pede a peça para verificar o movimento, enviando os dados destes para Movement que guarda as informações do movimento e depois as enviam para o tabuleiro atualizar a tela.

## Diagrama Geral de Componentes

![Diagrama Geral](diagrama-geral.png)

## Componente BoardComponent

O BoardComponent fornece um tabuleiro 9x9, onde serão colocadas peças e toda a função de manipulação destas serão feitas por ele.

![BoardComponent](diagrama-boardcomponent.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | BoardComponent
Autores | Thaina Milene de Oliveira
Interfaces | IBoard

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IBoard](diagrama-iboard.png)

## Detalhamento das Interfaces

### Interface IBoard

É responsável por toda a comunicação necessária entre as peças e o tabuleiro.

~~~java
public interface IBoard {
    void translate(int position) throws InvalidPlay;
    IPieces[] getBoard();
}
~~~

Método | Objetivo
-------| --------
translate | Reconhece o index das peças clicadas, e envia para uma interface do tipo ITranslateMovimentC para criar um objeto que guarda os movimentos.
getBoard | Retorna o atributo board, que guarda as peças

## Componente NormalPiecesComponent

O NormalPiecesComponent fornece peças normais, sendo nove tipos diferentes, cuja quantidade de peças distintas variam de acordo com o nível que o usuário escolher.

![NormalPiecesComponent](diagrama-normalpiecescomponent.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | NormalPiecesComponent
Autores | Thaina Milene de Oliveira
Interfaces | ICheckMovement, PiecesAttributes, ActionListener

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IPieces](diagrama-ipieces1.png)

Interface agregadora do componente em Java:

~~~java
public interface IPieces extends ICheckMovement, PiecesAttributes, ActionListener {
}
~~~

## Detalhamento das Interfaces

### Interface ICheckMoviment

É responsável por verificar se o movimento é válido.

~~~java
        moves[0] = new MovementComponent();
        moves[1] = new MovementComponent();
        if ((index - board[target].getIndex()) * (index - board[target].getIndex()) == 1) { // pecas movendo na mesma linha
            if (type == getPieceOnTop(target) && type == getPieceOnTop(target - 9)) {
                moves[0].setV(true);
                moves[0].setMoveType('c');
                moves[0].addVct(target);
                moves[0].addVct(target - 9);
                moves[0].addVct(target - 18);
                if (type == getPieceInBottom(target)) {
                    moves[0].setMoveType('1');
                    moves[0].addVct(target + 9);
                }
            }
            if (type == getPieceInBottom(target) && type == getPieceInBottom(target + 9)) {
                if (moves[0].isV()) {
                    moves[0].setMoveType('b');
                    moves[0].addVct(target + 18);
                } else {
                    moves[0].setV(true);
public interface ICheckMovement {
    boolean verifyMovement(int target) throws InvalidPlay;
}
~~~

Método | Objetivo
-------| --------
verifyMovement | recebe como parâmetro a posição da segunda peça clicada, e compara com a posição da própria peça, verificando se o movimento é válido.

### Interface PiecesAttributes

Dá acesso aos atributos das peças.

~~~java
public interface PiecesAttributes {
    void setType(int x);
    char getType();
    int getX();
    IMovement[] getMoves();
    void setIndex(int index);
    int getIndex();
    void setBoard(IBoard Board);
    JButton getButton();
}
~~~

Método | Objetivo
-------| --------
setType | recebe como parâmetro um número inteiro e a partir dele define o tipo da peça
getType | retorna o tipo da peça
getX | retorna o número inteiro que foi usado para definir o tipo da peça
getMoves | retorna o objeto com as informações sobre o movimento de cada jogada
setIndex | guarda o índice da peça no tabuleiro
getBoard | retorna o índice da peça no tabuleiro
getButton | retorna o botão associado a peça

## Componente Bonus01Component

O Bonus01Component fornece peças do tipo bonus01.

![Bonus01Component](diagrama-bonus01component.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | Bonus01Component
Autores | Thaina Milene de Oliveira
Interfaces | ICheckMovement, PiecesAttributes, ActionListener

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IPieces](diagrama-ipieces2.png)

Interface agregadora do componente em Java:

~~~java
public interface IPieces extends ICheckMovement, PiecesAttributes, ActionListener {
}
~~~

## Detalhamento das Interfaces

### Interface ICheckMoviment

É responsável por verificar se o movimento é válido.

~~~java
public interface ICheckMovement {
    boolean verifyMovement(int target) throws InvalidPlay;
}
~~~

Método | Objetivo
-------| --------
verifyMovement | recebe como parâmetro a posição da segunda peça clicada, e compara com a posição da própria peça, verificando se o movimento é válido.

### Interface PiecesAttributes

Dá acesso aos atributos das peças.

~~~java
public interface PiecesAttributes {
    void setType(int x);
    char getType();
    int getX();
    IMovement[] getMoves();
    void setIndex(int index);
    int getIndex();
    void setBoard(IBoard Board);
    JButton getButton();
}
~~~

Método | Objetivo
-------| --------
setType | recebe como parâmetro um número inteiro e a partir dele define o tipo da peça
getType | retorna o tipo da peça
getX | retorna o número inteiro que foi usado para definir o tipo da peça
getMoves | retorna o objeto com as informações sobre o movimento de cada jogada
setIndex | guarda o índice da peça no tabuleiro
getBoard | retorna o índice da peça no tabuleiro
getButton | retorna o botão associado a peça

## Componente Bonus02Component

O Bonus02Component fornece peças do tipo bonus01.

![Bonus02Component](diagrama-bonus02component.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | Bonus02Component
Autores | Thaina Milene de Oliveira
Interfaces | ICheckMovement, PiecesAttributes, ActionListener

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IPieces](diagrama-ipieces3.png)

Interface agregadora do componente em Java:

~~~java
public interface IPieces extends ICheckMovement, PiecesAttributes, ActionListener {
}
~~~

## Detalhamento das Interfaces

### Interface ICheckMoviment

É responsável por verificar se o movimento é válido.

~~~java
public interface ICheckMovement {
    boolean verifyMovement(int target) throws InvalidPlay;
}
~~~

Método | Objetivo
-------| --------
verifyMovement | recebe como parâmetro a posição da segunda peça clicada, e compara com a posição da própria peça, verificando se o movimento é válido.

### Interface PiecesAttributes

Dá acesso aos atributos das peças.

~~~java
public interface PiecesAttributes {
    void setType(int x);
    char getType();
    int getX();
    IMovement[] getMoves();
    void setIndex(int index);
    int getIndex();
    void setBoard(IBoard Board);
    JButton getButton();
}
~~~

Método | Objetivo
-------| --------
setType | recebe como parâmetro um número inteiro e a partir dele define o tipo da peça
getType | retorna o tipo da peça
getX | retorna o número inteiro que foi usado para definir o tipo da peça
getMoves | retorna o objeto com as informações sobre o movimento de cada jogada
setIndex | guarda o índice da peça no tabuleiro
getBoard | retorna o índice da peça no tabuleiro
getButton | retorna o botão associado a peça

## Componente Bonus03Component

O Bonus03Component fornece peças do tipo bonus01.

![Bonus03Component](diagrama-bonus03component.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | Bonus03Component
Autores | Thaina Milene de Oliveira
Interfaces | ICheckMovement, PiecesAttributes, ActionListener

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IPieces](diagrama-ipieces4.png)

Interface agregadora do componente em Java:

~~~java
public interface IPieces extends ICheckMovement, PiecesAttributes, ActionListener {
}
~~~

## Detalhamento das Interfaces

### Interface ICheckMoviment

É responsável por verificar se o movimento é válido.

~~~java
public interface ICheckMovement {
    boolean verifyMovement(int target) throws InvalidPlay;
}
~~~

Método | Objetivo
-------| --------
verifyMovement | recebe como parâmetro a posição da segunda peça clicada, e compara com a posição da própria peça, verificando se o movimento é válido.

### Interface PiecesAttributes

Dá acesso aos atributos das peças.

~~~java
public interface PiecesAttributes {
    void setType(int x);
    char getType();
    int getX();
    IMovement[] getMoves();
    void setIndex(int index);
    int getIndex();
    void setBoard(IBoard Board);
    JButton getButton();
}
~~~

Método | Objetivo
-------| --------
setType | recebe como parâmetro um número inteiro e a partir dele define o tipo da peça
getType | retorna o tipo da peça
getX | retorna o número inteiro que foi usado para definir o tipo da peça
getMoves | retorna o objeto com as informações sobre o movimento de cada jogada
setIndex | guarda o índice da peça no tabuleiro
getBoard | retorna o índice da peça no tabuleiro
getButton | retorna o botão associado a peça

## Componente ScoreboardComponent

O ScoreboardComponent é responsável pelo placar e contador de rodadas do jogo, além de verificar ao final, se o jogador ganhou ou perdeu, e encerrando o jogo.

![ScoreboardComponent](diagrama-scoreboardcomponent.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | ScoreboardComponent
Autores | Thaina Milene de Oliveira
Interfaces | IRound, IScore, ActionListener

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IScoreboard](diagrama-iscoreboard.png)

Interface agregadora do componente em Java:

~~~java
public interface IScoreboard extends IScore, IRound, ActionListener {
}
~~~

## Detalhamento das Interfaces

### Interface IScore

É responsável por atualizar o placar do jogo.

~~~java
public interface IScore {
    void sumScore(int n);
}
~~~

Método | Objetivo
-------| --------
sumScore | recebe como parâmetro a pontuação a ser somada ao placar e a soma

### Interface IRound

É responsável por atualizar o contador de rodadas

~~~java
public interface IRound {
    void sumRound();
}
~~~

Método | Objetivo
-------| --------
sumRound | soma mais um na rodada

## Componente MovementComponent

O MovementComponent é responsável por guardar as informações da rodada, como se ela é válida, o tipo de movimento, quais peças serão eliminadas.

![MovementComponent](diagrama-movementcomponent.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | MovementComponent
Autores | Thaina Milene de Oliveira
Interfaces | MovementComponentAttributes, IVector

### Interfaces

Interfaces associadas a esse componente:

![Diagrama IMovement](diagrama-imovement.png)

Interface agregadora do componente em Java:

~~~java
public interface IMovement extends MovementComponentAttributes, IVector {
}
~~~

## Detalhamento das Interfaces

### Interface MovementComponentAttributes


Dá acesso aos atributos da classe.

~~~java
public interface MovementComponentAttributes {

    void setMoveType(char moveType);
    void setV(boolean v);
    char getMovetype();
    int[] getVct();
    boolean isV();

}
~~~

Método | Objetivo
-------| --------
setMoveType | recebe como parâmetro um char que representa o tipo de movimento que a rodada gerará.
setV | recebe como parametro um boolean que define a jogada como possível pu não possível
getMovetype | retorna o tipo de movimento
getVct | retorna um vetor com as peças a serem eliminadas
isV() | retorna se a jogada é verdadeira ou falsa

### Interface IVector

É responsável por manipular o vetor contendo as peças a serem eliminadas

~~~java
public interface IVector {
    void addVct(int index);
    void removeVct();
}
~~~

Método | Objetivo
-------| --------
addVct | adiciona um índice ao vetor, na posição que o contador estivel
removeVct() | faz um contador voltar uma posição, para assim o último elemento ser sobrescrito por outro

## Componente TranslateMovementComponent

O TranslateMovementComponent é responsável por traduzir e guardar em um objeto os índices das peças trocadas.

![TranslateMovementComponent](diagrama-translatemovementcomponent.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | TranslateMovementComponent
Autores | Thaina Milene de Oliveira
Interfaces | ITranslateMovementC

### Interfaces

Interfaces associadas a esse componente:

![Diagrama ITranslateMovementC](diagrama-itranslatemovementc.png)

## Detalhamento da Interface

### Interface ITranslateMovementC


Dá acesso aos atributos da classe.

~~~java
public interface ITranslateMovementC {
    int getSource();
    int getTarget();
    void setTarget(int target) throws NonAdjacentPieces;
    void setSource(int source);
}
~~~

Método | Objetivo
-------| --------
setSource | recebe como parâmetro um inteiro que representa a posição da primeira peça clicada
setTarget | recebe como parâmetro um inteiro que representa a posição da segunda peça clicada
getSource| retorna a posição da primeira peça
getTarget| retorna a posição da segunda peça

# Plano de Exceções1

## Diagrama da hierarquia de exceções

![Hierarquia Exceções](exception-hierarchy.png)

## Descrição das classes de exceção

Classe | Descrição
----- | -----
InvalidPlay | Engloba todas as exceções de jogadas não aceitas.
NonAdjacentPieces | Indica que foi clicado duas peças não adjacentes.
UselessMovement | Indica que o movimento não gera uma jogada válida
