#include <mbgl/annotation/annotation_source.hpp>
#include <mbgl/annotation/annotation_manager.hpp>
#include <mbgl/annotation/annotation_tile.hpp>

namespace mbgl {

using namespace style;

AnnotationSource::AnnotationSource()
    : Source(SourceType::Annotations, std::make_unique<Impl>(*this)) {
}

AnnotationSource::Impl::Impl(Source& base_)
    : Source::Impl(SourceType::Annotations, AnnotationManager::SourceID, base_) {
}

Range<uint8_t> AnnotationSource::Impl::getZoomRange() {
    // Same as default geojson-vt-cpp.
    return { 0, 18 };
}

void AnnotationSource::Impl::load(FileSource&) {
    loaded = true;
}

std::unique_ptr<Tile> AnnotationSource::Impl::createTile(const OverscaledTileID& tileID,
                                                         const style::UpdateParameters& parameters) {
    return std::make_unique<AnnotationTile>(tileID, parameters);
}

} // namespace mbgl
