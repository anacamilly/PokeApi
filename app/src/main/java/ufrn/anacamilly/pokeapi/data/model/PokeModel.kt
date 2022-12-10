package ufrn.anacamilly.pokeapi.data.model

data class PokeModel(val id:Int= -1,
                     val name:String ="",
                     val height:Double = 0.0,
                     val weight:Double = 0.0,
                     val base_experience:Long = 0,
                     val sprites: PokeSprites)
