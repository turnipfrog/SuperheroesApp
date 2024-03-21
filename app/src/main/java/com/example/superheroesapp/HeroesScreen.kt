package com.example.superheroesapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.model.HeroesRepository
import com.example.superheroesapp.ui.theme.Shapes
import com.example.superheroesapp.ui.theme.SuperheroesAppTheme

@Composable
fun Superhero(
    hero: Hero,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clip(Shapes.medium)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column (
                Modifier
                    .padding(end = dimensionResource(R.dimen.padding_medium))
                    .weight(1f)
            ){
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Box(Modifier.size(dimensionResource(R.dimen.image_size))) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .clip(Shapes.small)
                )
            }
        }
    }
}

@Composable
fun HeroList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding) {
        items(heroes) {
            Superhero(
                hero = it,
                modifier = Modifier.padding(
                    bottom = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Preview
@Composable
fun SuperheroListPreview() {
    SuperheroesAppTheme(darkTheme = false) {
        HeroList(HeroesRepository.heroes)
    }
}