# language: pt

Funcionalidade: Consulta de informações de um personagem

  Esquema do Cenario: Consulta de personagem válido
    Dado o personagem com o nome "<name>"
    Quando for consultado as informações
    Entao ele deve retornar a seguinte resposta
    """
    {
      "characterDescription": "<characterDescription>",
      "image": "<image>"
    }
    """

    Exemplos:
      | name   | characterDescription                                                                                                                                                                           | image                                                                                                  |
      | Walter | Walter White was born in 07/09/1958, has Heisenberg as nickname, the character occupation is high school chemistry teacher and meth king pin, is presumed dead and is acted by Bryan Cranston. | https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg |

