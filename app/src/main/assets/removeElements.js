function removeUnwantedElements() {
    var header = document.querySelector('.home__header');
    if (header) {
        header.parentNode.removeChild(header);
    }

    var footer = document.querySelector('.footer-wrap');
    if (footer) {
        footer.parentNode.removeChild(footer);
    }

    var banners = document.querySelectorAll('.flex-center.banner.at-a-class.img.skpt_3rd');
    banners.forEach(function(banner) {
        banner.parentNode.removeChild(banner);
    });

    var detailTopHeader = document.querySelector('.detail__top_header[data-v-ef1b8a26]');
    if (detailTopHeader) {
        detailTopHeader.parentNode.removeChild(detailTopHeader);
    }

    var elementsToDelete = document.querySelectorAll('a[data-v-31cc43be][data-v-ef1b8a26][href="https://www.score808bet.com/guess/picks"]');
    elementsToDelete.forEach(function(element) {
        element.parentNode.removeChild(element);
    });
}

removeUnwantedElements();
