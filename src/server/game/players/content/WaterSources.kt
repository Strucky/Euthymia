package server.game.players.content

import server.game.players.Client

class WaterSources {

    companion object {
        private const val EMPTY_VIAL = 229
        private const val VIAL_WATER = 227
        private const val EMPTY_BUCKET = 1925
        private const val WATER_BUCKET = 1929

        private val waterContainer = mapOf(
                EMPTY_VIAL to VIAL_WATER,
                EMPTY_BUCKET to WATER_BUCKET
        )

        private val waterSources = hashSetOf(
                21355, 873, 874, 4063, 6151, 8699, 9143, 9684, 10175, 12279, 12974, 13563,
                13564, 879, 880, 2638, 2864, 6232, 10436, 10437, 10827, 11007, 11759, 13478, 13479, 153
        )

        @JvmStatic
        fun containerOnWater(player: Client, objectId: Int, itemId: Int) {
            if (waterContainer.containsKey(itemId) && waterSources.contains(objectId)) {
                player.getItems().deleteItem(itemId, 1);
                player.getItems().addItem(waterContainer[itemId]!!, 1);
            }
        }
    }
}