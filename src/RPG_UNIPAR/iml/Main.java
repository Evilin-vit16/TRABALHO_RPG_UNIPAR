package RPG_UNIPAR.iml;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int inimigosMortos = 0;

    static int ouro = 0;
    static int pocoes = 0;
    static int xp = 0;
    static int nivel = 1;
    static final int XP_POR_NIVEL = 50;
    static final int PRECO_POCAO = 30;
    static final int PRECO_UPGRADE_ATAQUE = 50;

    public static void mostrarInicio() {
        System.err.println("*******************");
        System.err.println("     JOGO RPG      ");
        System.err.println("*******************");
    }

    public static int escolhaPersonagem(Scanner sc) {

        int opcaoClasse = 0;

        do {
            System.out.println("Escolha sua classe :");
            System.out.println("1- Guerreiro");
            System.out.println("2- Arqueiro");
            System.out.println("3- Shaman");
            System.out.println("4- Mago");

            opcaoClasse = sc.nextInt();

        } while (opcaoClasse <= 0 || opcaoClasse >= 5);

        return opcaoClasse;
    }

    public static int definirVida(int opcao) {

        if (opcao == 1) {
            return 120;
        } else if (opcao == 2) {
            return 100;
        } else if (opcao == 3) {
            return 80;
        } else {
            return 90;
        }
    }

    public static int definirAtaque(int opcao) {

        if (opcao == 1) {
            return 80;
        } else if (opcao == 2) {
            return 70;
        } else if (opcao == 3) {
            return 50;
        } else {
            return 40;
        }
    }

    public static int definirDefesa(int opcao) {

        if (opcao == 1) {
            return 50;
        } else if (opcao == 2) {
            return 40;
        } else if (opcao == 3) {
            return 60;
        } else {
            return 20;
        }
    }

    public static int sortearDadoD20() {
        Random random = new Random();
        return random.nextInt(20) + 1;
    }

    public static int explorar() {

        Random random = new Random();
        int sorteio = random.nextInt(100) + 1;

        if (sorteio <= 40) {
            return 1;
        } else if (sorteio <= 60) {
            return 2;
        } else if (sorteio <= 95) {
            return 3;
        } else {
            return 4;
        }
    }

    public static void mostrarMenuJogo() {

        System.out.println("Inicio da jornada");
        System.out.println("1-Explorar");
        System.out.println("2-Ver Status");
        System.out.println("3-Recuperar");
        System.out.println("4-Usar Pocao");
        System.out.println("5-Loja");
        System.out.println("0-Sair");
    }

    public static void mostrarMenuCombate() {

        System.out.println("Você quer fazer uquê ?");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Fugir");
    }

    public static int atacarMonstro(int vidaMostro, int ataque) {

        int sorteioDado = sortearDadoD20();

        if (sorteioDado <= 7) {
            System.out.println("Você errou !");
        } else if (sorteioDado <= 14) {
            vidaMostro -= ataque / 2;
            System.out.println("Metade do dano");
        } else if (sorteioDado <= 19) {
            vidaMostro -= ataque;
            System.out.println("Ataque total");
        } else {
            vidaMostro -= ataque * 2;
            System.out.println("ATAQUE CRITICO");
        }

        return vidaMostro;
    }

    public static int receberAtaque(int vida, int ataqueMostro) {

        int sorteioDado = sortearDadoD20();

        if (sorteioDado <= 7) {
            System.out.println("O Goblin errou !");
        } else if (sorteioDado <= 14) {
            vida -= ataqueMostro / 2;
            System.out.println("Ataque pela metade");
        } else if (sorteioDado <= 19) {
            vida -= ataqueMostro;
            System.out.println("Você recebeu o ataque total");
        } else {
            vida -= ataqueMostro * 2;
            System.out.println("Você recebeu um critico");
        }

        return vida;
    }

    public static void mostrarVidas(int vida, int vidaMostro) {

        System.err.println("Vida Personagem : " + vida);
        System.err.println("Vida Mostro : " + vidaMostro);
    }

    public static int combater(int vida, int ataque, int defesa,
                               boolean temArma, boolean temArmadura) {

        Random random = new Random();

        int mostroEspecial = random.nextInt(20) + 1;
        int vidaMostro = random.nextInt(80) + 40 + mostroEspecial;
        int ataqueMostro = random.nextInt(30) + 5;

        if (vidaMostro >= 100) {
            System.out.println("Você encontrou o Marcelo Goblin!");
        }

        if (temArmadura) {
            defesa += 20;
        }

        if (temArma) {
            ataque += 20;
        }

        System.out.println("Seu inimigo tem :");
        System.out.println("Vida: " + vidaMostro);
        System.out.println("Ataque: " + ataqueMostro);

        while (vidaMostro > 0 && vida > 0) {

            vidaMostro = atacarMonstro(vidaMostro, ataque);

            mostrarVidas(vida, vidaMostro);

            if (vidaMostro > 0) {

                vida = receberAtaque(vida, ataqueMostro);

                mostrarVidas(vida, vidaMostro);
            }
        }

        return mostrarResultadoCombate(vida, vidaMostro);
    }

    public static int mostrarResultadoCombate(int vida, int vidaMostro) {

        if (vidaMostro > 0) {
            System.out.println(" O Goblin te pegou !");
            return 0;
        } else {
            System.out.println(" Você MATOU ele ! ");
            // PARTE 2: conta o inimigo morto
            inimigosMortos++;
            // PARTE 3: recompensas por matar o inimigo
            receberRecompensas();
            return vida;
        }
    }

    public static int enfrentarGoblin(Scanner sc, int vida,
                                      int ataque, int defesa,
                                      boolean temArma, boolean temArmadura) {

        System.out.println("Você encontrou um Goblin");

        mostrarMenuCombate();

        int opcaoLuta = sc.nextInt();

        if (opcaoLuta == 1) {

            vida = combater(vida, ataque, defesa, temArma, temArmadura);

        } else if (opcaoLuta == 2) {

        } else if (opcaoLuta == 3) {

            System.out.println("Corrrreeeuuuu !!");

        } else {

            System.out.println("Opção invalida");
        }

        return vida;
    }

    public static int encontrarEnergia(int energia) {

        System.out.println("Você encontrou um bau de energia");

        if (energia + 1 < 10) {
            energia += 1;
        } else {
            System.out.println("Energia cheia !");
        }

        return energia;
    }

    public static int sortearItemRaro() {

        Random random = new Random();
        return random.nextInt(2) + 1;
    }

    public static void mostrarStatus(String nome, int ataque,
                                     int defesa, int vida, int energia,
                                     boolean temArmadura, boolean temArma) {

        System.out.println(" Olá " + nome);
        System.out.println(" Ataque : " + ataque);
        System.out.println(" Defesa : " + defesa);
        System.out.println(" Vida :" + vida);
        System.out.println(" Energia: " + energia);
        System.out.println(" Armadura : " + (temArmadura ? "Sim" : "Não"));
        System.out.println(" Arma : " + (temArma ? "Sim" : "Não"));
    }

    public static int recuperarVida(int vida, int vidaMaxima) {

        vida += 10;

        if (vida >= vidaMaxima) {
            vida = vidaMaxima;
            System.out.println("Vida maxima atingida !");
        } else {
            System.out.println("Vida aumentada !");
        }

        return vida;
    }

    public static int recuperarEnergia(int energia) throws InterruptedException {

        System.out.println("Vamos recupar sua energia..");

        Thread.sleep(2000);

        System.out.println("Ainda estamos recuperando");

        Thread.sleep(3000);

        System.out.println("Energia recuperada !!");

        energia += 1;

        return energia;
    }

    public static void receberRecompensas() {

        Random random = new Random();

        int ouroGanho = random.nextInt(21) + 10;
        ouro += ouroGanho;
        System.out.println("Você ganhou " + ouroGanho + " de ouro !");

        int xpGanho = random.nextInt(16) + 20;
        xp += xpGanho;
        System.out.println("Você ganhou " + xpGanho + " de XP !");

        if (random.nextInt(100) < 30) {
            pocoes++;
            System.out.println("O Goblin dropou uma Pocao de Cura !");
        }
    }

    public static int verificarNivel() {

        int niveisGanhos = 0;

        while (xp >= XP_POR_NIVEL) {
            xp -= XP_POR_NIVEL;
            nivel++;
            niveisGanhos++;
            System.out.println("*** LEVEL UP! Você chegou ao nivel " + nivel + " ! ***");
            System.out.println("Bonus: +10 vida maxima e +5 ataque");
        }

        return niveisGanhos;
    }

    // PARTE 3 - Funcionalidade 2: Pocao de cura (inventario)
    public static int usarPocao(int vida, int vidaMaxima) {

        if (pocoes <= 0) {
            System.out.println("Você não tem pocoes !");
            return vida;
        }

        if (vida >= vidaMaxima) {
            System.out.println("Sua vida já está cheia !");
            return vida;
        }

        pocoes--;
        vida += 40;

        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }

        System.out.println("Você usou uma pocao e recuperou vida ! Pocoes restantes: " + pocoes);

        return vida;
    }

    public static int abrirLoja(Scanner sc, int ataque) {

        System.out.println("===== LOJA =====");
        System.out.println("Seu ouro: " + ouro);
        System.out.println("1 - Pocao de Cura (" + PRECO_POCAO + " ouro)");
        System.out.println("2 - Upgrade de Ataque +5 (" + PRECO_UPGRADE_ATAQUE + " ouro)");
        System.out.println("0 - Voltar");

        int opcaoLoja = sc.nextInt();

        if (opcaoLoja == 1) {

            if (ouro >= PRECO_POCAO) {
                ouro -= PRECO_POCAO;
                pocoes++;
                System.out.println("Você comprou uma Pocao de Cura !");
            } else {
                System.out.println("Ouro insuficiente !");
            }

        } else if (opcaoLoja == 2) {

            if (ouro >= PRECO_UPGRADE_ATAQUE) {
                ouro -= PRECO_UPGRADE_ATAQUE;
                ataque += 5;
                System.out.println("Seu ataque aumentou em 5 !");
            } else {
                System.out.println("Ouro insuficiente !");
            }
        }

        return ataque;
    }

    public static void mostrarProgresso() {

        System.out.println(" Nivel : " + nivel);
        System.out.println(" XP : " + xp + "/" + XP_POR_NIVEL);
        System.out.println(" Ouro : " + ouro);
        System.out.println(" Pocoes : " + pocoes);
        System.out.println(" Inimigos mortos : " + inimigosMortos);
    }

    public static void mostrarResumoFinal() {

        System.out.println("===== FIM DE JOGO =====");
        System.out.println("Inimigos mortos: " + inimigosMortos);
        System.out.println("Nivel alcançado: " + nivel);
        System.out.println("Ouro acumulado: " + ouro);
    }

    public static void main(String[] args) throws InterruptedException {

        mostrarInicio();

        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome do seu personagem");
        String nome = sc.next();

        int opcao = escolhaPersonagem(sc);

        int vida = definirVida(opcao);
        int ataque = definirAtaque(opcao);
        int defesa = definirDefesa(opcao);

        int energia = 0;
        boolean temArma = false;
        boolean temArmadura = false;

        System.out.println("Seja bem-vindo " + nome + " ! ");

        int opcaoJogo = -1;
        int vidaMaxima = vida;

        while (opcaoJogo != 0) {

            mostrarMenuJogo();

            opcaoJogo = sc.nextInt();

            if (opcaoJogo == 1) {

                int opcaoExplorar = explorar();

                if (opcaoExplorar == 1) {

                    vida = enfrentarGoblin(sc, vida, ataque, defesa,
                            temArma, temArmadura);

                    if (vida <= 0) {
                        System.err.println("VOCê MORREUUU !");
                        break;
                    }

                    int niveisGanhos = verificarNivel();
                    if (niveisGanhos > 0) {
                        vidaMaxima += 10 * niveisGanhos;
                        vida += 10 * niveisGanhos;
                        ataque += 5 * niveisGanhos;
                    }

                } else if (opcaoExplorar == 2) {

                    energia = encontrarEnergia(energia);

                } else if (opcaoExplorar == 3) {

                    System.out.println("Você deve seguir sua vidinha");

                } else if (opcaoExplorar == 4) {

                    System.out.println("Sortudinho, ganhou um item raro");

                    int itemSorteado = sortearItemRaro();

                    if (itemSorteado == 1) {

                        if (temArmadura) {
                            System.out.println("Você já tem, rala");
                        } else {
                            temArmadura = true;
                        }

                    } else {

                        if (temArma) {
                            System.out.println("Você ja ta armado ! ");
                        } else {
                            temArma = true;
                        }
                    }
                }

            } else if (opcaoJogo == 2) {

                mostrarStatus(nome, ataque, defesa, vida, energia,
                        temArmadura, temArma);
                mostrarProgresso();

            } else if (opcaoJogo == 3) {

                vida = recuperarVida(vida, vidaMaxima);

                energia = recuperarEnergia(energia);

            } else if (opcaoJogo == 4) {

                vida = usarPocao(vida, vidaMaxima);

            } else if (opcaoJogo == 5) {

                ataque = abrirLoja(sc, ataque);

            } else {

                break;
            }
        }

        mostrarResumoFinal();
    }
}